package com.endiel.jobfinder.ui.screens.jobdetails

import androidx.compose.animation.SharedTransitionScope
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.endiel.jobfinder.data.repository.ApplicationRepository
import com.endiel.jobfinder.data.repository.AuthRepository
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
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _application = MutableStateFlow<ApplicationItem?>(null)
    val application: StateFlow<ApplicationItem?> = _application.asStateFlow()

    fun loadApplication(applicationId: String) {
        val userId = authRepository.getCurrentUserId() ?: return

        viewModelScope.launch {
            applicationRepository.getApplicationById(applicationId, userId).collect { application ->
                _application.value = application?.let {
                    ApplicationItem(
                        application = it,
                        job = com.endiel.jobfinder.model.Job(
                            id = it.jobId,
                            companyName = it.companyName.ifBlank { "Unknown Company" },
                            jobTitle = it.jobTitle.ifBlank { "Unknown Job" },
                            location = it.location.ifBlank { "Unknown Location" },
                            salary = "Salary not specified",
                            category = "Other",
                            description = "",
                            jobType = "Unknown",
                            jobPosition = "Unknown",
                            requirements = emptyList(),
                            responsibilities = emptyList()
                        )
                    )
                }
            }
        }
    }

    fun updateApplicationStatus(applicationId: String, status: ApplicationStatus) {
        viewModelScope.launch {
            applicationRepository.updateApplicationStatus(
                applicationId = applicationId,
                status = status
            )
        }
    }
}