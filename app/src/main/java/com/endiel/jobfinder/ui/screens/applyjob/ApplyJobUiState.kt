package com.endiel.jobfinder.ui.screens.applyjob

data class ApplyJobUiState(
    val fullName: String = "",
    val email: String = "",
    val phone: String = "",
    val coverLetter: String = "",
    val isSubmitting: Boolean = false,
    val isSubmitted: Boolean = false,
    val error: String? = null,
    val submitSuccess: Boolean = false
)