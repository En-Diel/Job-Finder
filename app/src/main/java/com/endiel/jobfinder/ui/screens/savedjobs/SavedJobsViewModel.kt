package com.endiel.jobfinder.ui.screens.savedjobs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.endiel.jobfinder.data.repository.SavedJobsRepository
import com.endiel.jobfinder.model.Job
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class SavedJobsViewModel @Inject constructor(repository: SavedJobsRepository) : ViewModel() {
    val savedJobs: StateFlow<List<Job>> = repository.getSavedJobs()
        .stateIn(viewModelScope,
        SharingStarted
            .WhileSubscribed(5_000),
            emptyList()
        )
}