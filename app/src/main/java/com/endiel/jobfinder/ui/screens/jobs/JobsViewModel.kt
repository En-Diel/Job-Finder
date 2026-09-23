package com.endiel.jobfinder.ui.screens.jobs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.endiel.jobfinder.data.repository.JobsRepository
import com.endiel.jobfinder.data.repository.SavedJobsRepository
import com.endiel.jobfinder.model.Job
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

@HiltViewModel
class JobsViewModel @Inject constructor(
    private val repository: JobsRepository,
    private val savedJobsRepository: SavedJobsRepository
): ViewModel() {

    // Saved job state
    val savedJobs: StateFlow<List<Job>> = savedJobsRepository.getSavedJobs().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )
    // Ui State
    private val _uiState = MutableStateFlow(JobsUiState())
    val uiState: StateFlow<JobsUiState> = _uiState.asStateFlow()

    // Search state
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    // Category state
    private val _selectedCategory = MutableStateFlow("All")
    val selectedCategory: StateFlow<String> = _selectedCategory.asStateFlow()

    private var searchJob: kotlinx.coroutines.Job? = null

    // Categories
    val categories = listOf(
        "All",
        "Technology",
        "Design",
        "Finance",
        "Marketing",
        "Sales",
        "Engineering"
    )

    // Filtered jobs
    val filteredJobs: StateFlow<List<Job>> = combine(
        uiState,
        searchQuery,
        selectedCategory
    ) { state, query, category ->
        state.jobs.filter { job ->
            val matchesSearch =
                job.jobTitle.contains(
                    query,
                    ignoreCase = true
                ) ||
                        job.companyName.contains(
                            query,
                            ignoreCase = true
                        )

            val matchesCategory =
                category == "All" ||
                        job.category.equals(
                            category,
                            ignoreCase = true
                        )
            matchesSearch && matchesCategory
        }
        // We use stateIn coz combine gives us Flow<List<Job>>, and UI want StateFlow<List<Job>>
    }.stateIn(
        // Means run this inside the lifecycle of my viewModel
        scope = viewModelScope,
        // Means keep the flow active while is being observed, if not wait 5 sec before stopping it
        started = SharingStarted.WhileSubscribed(5_000),
        // the first value when flow hasn't produced it's first result yet
        initialValue = emptyList()
    )

    // Initialization
    init {
        loadJobs()
    }

    // Load jobs
    fun loadJobs(query: String? = null) {
        viewModelScope.launch {
            _uiState.value = JobsUiState(
                isLoading = true
            )
            try {
                repository.getJobs(query).collect { jobs ->
                    _uiState.value = JobsUiState(
                        jobs = jobs,
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                _uiState.value = JobsUiState(
                    isLoading = false,
                    error = "Failed to load jobs."
                )
            }
        }
    }

    // Search
    fun onSearchQueryChange(query: String) {

        _searchQuery.value = query
        searchJob?.cancel()
        viewModelScope.launch {
            // Make the search delay a bit until the user finishes typing and then perform search
            kotlinx.coroutines.delay(500.milliseconds)
            if(query.isBlank()) {
                loadJobs()
            } else {
                loadJobs(query)
            }
        }
    }

    // Category selection
    fun onCategorySelected(category: String) {
        _selectedCategory.value = category
    }

    fun isJobSaved(jobId: String): Boolean {
        return savedJobs.value.any { it.id == jobId }
    }

    fun toggleSaveJob(job: Job) {
        viewModelScope.launch {
            if (isJobSaved(job.id)) {
                savedJobsRepository.removeJob(job)
            } else {
                savedJobsRepository.saveJob(job)
            }
        }
    }
}