package com.endiel.jobfinder.ui.screens.jobs

import com.endiel.jobfinder.model.Job

data class JobsUiState (
    val jobs: List<Job> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)