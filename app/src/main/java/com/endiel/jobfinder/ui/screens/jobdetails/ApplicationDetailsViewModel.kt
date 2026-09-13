package com.endiel.jobfinder.ui.screens.jobdetails

import androidx.compose.animation.SharedTransitionScope
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.endiel.jobfinder.data.repository.ApplicationRepository
import com.endiel.jobfinder.data.repository.JobsRepository
import com.endiel.jobfinder.model.ApplicationStatus
import com.endiel.jobfinder.ui.screens.applications.ApplicationItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ApplicationDetailsViewModel @Inject constructor(
    private val applicationRepository: ApplicationRepository,
    private val jobsRepository: JobsRepository
) : ViewModel() {

    private val _application = MutableStateFlow<ApplicationItem?>(null)
    val application: StateFlow<ApplicationItem?> = _application.asStateFlow()

    fun loadApplication(applicationId: Int) {
        viewModelScope.launch {
            applicationRepository.getApplicationById(applicationId).collect { application ->
                _application.value = application?.let {
                    ApplicationItem(
                        application = it,
                        job = jobsRepository.getJobById(it.jobId)
                    )
                }
            }
        }
    }

    fun updateApplicationStatus(applicationId: Int, status: ApplicationStatus) {
        viewModelScope.launch {
            applicationRepository.updateApplicationStatus(
                applicationId = applicationId,
                status = status
            )
        }
    }
}