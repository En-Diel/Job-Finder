package com.endiel.jobfinder.ui.screens.savedjobs

import androidx.lifecycle.ViewModel
import com.endiel.jobfinder.data.repository.SavedJobsRepository
import com.endiel.jobfinder.model.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SavedJobsViewModel : ViewModel() {

    // The viewModel now owns the saved jobs screen state
    private val _savedJobs = MutableStateFlow<List<Job>>(emptyList())

    val savedJobs: StateFlow<List<Job>> = _savedJobs.asStateFlow()

    fun loadSavedJobs() {
        _savedJobs.value = SavedJobsRepository.getSavedJobs()
    }
    fun removeJob(job: Job) {
        SavedJobsRepository.removeJob(job)
        loadSavedJobs()
    }
}