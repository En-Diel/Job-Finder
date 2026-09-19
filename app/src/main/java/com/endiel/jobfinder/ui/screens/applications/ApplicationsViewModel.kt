package com.endiel.jobfinder.ui.screens.applications

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.endiel.jobfinder.data.local.JobApplicationEntity
import com.endiel.jobfinder.data.repository.ApplicationRepository
import com.endiel.jobfinder.data.repository.JobsRepository
import com.endiel.jobfinder.model.ApplicationStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.collections.emptyList

@HiltViewModel
class ApplicationsViewModel @Inject constructor(
    private val applicationRepository: ApplicationRepository,
    private val jobsRepository: JobsRepository
) : ViewModel() {

    val applications: StateFlow<List<ApplicationItem>> =
        applicationRepository.getApplications().map { applicationList ->
            applicationList.map { application ->
                ApplicationItem(
                    application = application,
                    job = jobsRepository.getJobById(application.jobId)
                )
            }
        }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000), initialValue = emptyList()
            )

    init {
        viewModelScope.launch {
            applicationRepository.syncApplications()
        }
    }

    fun getApplicationById(applicationId: String): ApplicationItem? {
        return applications.value.find {
            it.application.id == applicationId
        }
    }
}