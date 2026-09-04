package com.endiel.jobfinder.ui.screens.jobs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.endiel.jobfinder.data.repository.JobsRepository
import com.endiel.jobfinder.data.repository.sampleJobs
import com.endiel.jobfinder.model.Job
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
class JobsViewModel @Inject constructor(private val repository: JobsRepository): ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _selectedCategory = MutableStateFlow("All")
    val selectedCategory: StateFlow<String> = _selectedCategory.asStateFlow()

    val categories = listOf(
        "All",
        "Technology",
        "Design",
        "Finance",
        "Marketing",
        "Sales",
        "Engineering"
    )
    private val allJobs = MutableStateFlow(sampleJobs)
    val filteredJobs: StateFlow<List<Job>> = combine(
        allJobs,
        searchQuery,
        selectedCategory
        ) { jobs, query, category ->
            jobs.filter { job ->
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
            initialValue = sampleJobs
        )

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
    }

    fun onCategorySelected(category: String) {
        _selectedCategory.value = category
    }
}