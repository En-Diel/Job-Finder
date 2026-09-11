package com.endiel.jobfinder.ui.screens.profile

data class ProfileUiState(
    val fullName: String = "",
    val email: String = "",
    val phone: String = "",
    val location: String = "",
    val isEditing: Boolean = false
)