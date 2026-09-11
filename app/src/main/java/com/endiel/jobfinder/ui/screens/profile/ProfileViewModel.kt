package com.endiel.jobfinder.ui.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.endiel.jobfinder.data.repository.ProfileRepository
import com.endiel.jobfinder.model.UserProfile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val repository: ProfileRepository): ViewModel() {
    private val _uiState = MutableStateFlow(
        ProfileUiState()
    )

    val uiState: StateFlow<ProfileUiState> =
        _uiState.asStateFlow()

    init {
        loadProfile()
    }
    private fun loadProfile() {

        viewModelScope.launch {

            repository.getProfile()
                .collect { profile ->

                    if (profile != null) {

                        _uiState.update {
                            it.copy(
                                fullName = profile.fullName,
                                email = profile.email,
                                phone = profile.phone,
                                location = profile.location
                            )
                        }

                    } else {

                        val defaultProfile = UserProfile(
                            fullName = "John Doe",
                            email = "johndoe@email.com",
                            phone = "+255 700 000 000",
                            location = "Dar es Salaam, Tanzania"
                        )
                        repository.saveProfile(defaultProfile)
                        _uiState.update {
                            it.copy(
                                fullName = defaultProfile.fullName,
                                email = defaultProfile.email,
                                phone = defaultProfile.phone,
                                location = defaultProfile.location
                            )
                        }
                        }
                    }

        }
    }

    fun onFullNameChange(value: String) {
        _uiState.update {
            it.copy(fullName = value)
        }
    }
    fun onEmailChange(value: String) {
        _uiState.update {
            it.copy(email = value)
        }
    }

    fun onPhoneChange(value: String) {
        _uiState.update {
            it.copy(phone = value)
        }
    }

    fun onLocationChange(value: String) {
        _uiState.update {
            it.copy(location = value)
        }
    }

    fun startEditing() {
        _uiState.update {
            it.copy(isEditing = true)
        }
    }
    fun saveProfile() {
        _uiState.update {
            it.copy(isEditing = false)
        }
    }
}