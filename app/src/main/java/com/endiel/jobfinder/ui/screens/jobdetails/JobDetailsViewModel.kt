package com.endiel.jobfinder.ui.screens.jobdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.endiel.jobfinder.data.repository.SavedJobsRepository
import com.endiel.jobfinder.model.Job
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.Job as CoroutineJob

@HiltViewModel
class JobDetailsViewModel @Inject constructor(private val repository: SavedJobsRepository) : ViewModel() {

    private val _isSaved = MutableStateFlow(false)
    val isSaved: StateFlow<Boolean> = _isSaved.asStateFlow()

    private var observationJob: CoroutineJob? = null

    fun loadJob(job: Job) {

        observationJob?.cancel()

        observationJob = viewModelScope.launch {
            repository.isJobSaved(job.id).collect { saved ->
                _isSaved.value = saved
            }
        }
    }
    fun toggleSaveJob(job: com.endiel.jobfinder.model.Job) {
        viewModelScope.launch {
            if (_isSaved.value) {
                repository.removeJob(job)
            } else {
                repository.saveJob(job)
            }
        }
    }
}