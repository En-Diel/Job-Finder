package com.endiel.jobfinder.ui.screens.savedjobs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.endiel.jobfinder.data.repository.SavedJobsRepository
import com.endiel.jobfinder.model.Job
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class SavedJobsViewModel @Inject constructor(
    private val repository: SavedJobsRepository
) : ViewModel() {
    // Saved jobs
    val savedJobs: StateFlow<List<Job>> =
        repository.getSavedJobs()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = emptyList()
            )
    // Remove saved job
    fun removeJob(job: Job) {
        viewModelScope.launch {
            repository.removeJob(job)
        }
    }
}