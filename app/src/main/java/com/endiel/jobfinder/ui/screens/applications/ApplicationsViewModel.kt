package com.endiel.jobfinder.ui.screens.applications

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.endiel.jobfinder.data.local.JobApplicationEntity
import com.endiel.jobfinder.data.repository.ApplicationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ApplicationsViewModel @Inject constructor(private val repository: ApplicationRepository) :
    ViewModel() {
    val applications: StateFlow<List<JobApplicationEntity>> = repository.getApplications()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000), initialValue = emptyList()
        )
}