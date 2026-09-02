package com.endiel.jobfinder.ui.screens.jobdetails

import androidx.lifecycle.ViewModel
import com.endiel.jobfinder.data.repository.SavedJobsRepository
import com.endiel.jobfinder.model.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class JobDetailsViewModel: ViewModel() {

    private val _isSaved = MutableStateFlow(false)
    val isSaved: StateFlow<Boolean> = _isSaved.asStateFlow()

    fun loadJob(job: Job) {
        _isSaved.value = SavedJobsRepository.isJobSaved(job)
    }
    fun toggleSaveJob(job: Job) {
        if (_isSaved.value) {
            SavedJobsRepository.removeJob(job)
        } else {
            SavedJobsRepository.saveJob(job)
        }
        _isSaved.value = !_isSaved.value
    }
}