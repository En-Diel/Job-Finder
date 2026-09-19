package com.endiel.jobfinder.ui.screens.applyjob

import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.endiel.jobfinder.data.repository.ApplicationRepository
import com.endiel.jobfinder.model.JobApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ApplyJobViewModel @Inject constructor(private val repository: ApplicationRepository) :
    ViewModel() {
    // _uiState (contains the current state of the UI)
    //

    private val _uiState = MutableStateFlow(ApplyJobUiState())
    val uiState: StateFlow<ApplyJobUiState> = _uiState.asStateFlow()

    fun onFullNameChange(value: String) {
        _uiState.update { it.copy(fullName = value) }
    }

    fun onEmailChange(value: String) {
        _uiState.update { it.copy(email = value) }
    }

    fun onPhoneChange(value: String) {
        _uiState.update { it.copy(phone = value) }
    }

    fun onCoverLetterChange(value: String) {
        _uiState.update { it.copy(coverLetter = value) }
    }

    fun submitApplication(jobId: String) {
        val state = _uiState.value
        if (state.fullName.isBlank() ||
            state.email.isBlank() ||
            state.phone.isBlank() ||
            state.coverLetter.isBlank()
            ) {
            _uiState.update {
                it.copy(error = "Please fill in all fields")
            }
            return
        }
        viewModelScope.launch {
            _uiState.update {
                it.copy(isSubmitting = true, error = null)
            }

            try {
                repository.submitApplication(
                    JobApplication(
                        jobId = jobId,
                        fullName = state.fullName,
                        email = state.email,
                        phone = state.phone,
                        coverLetter = state.coverLetter
                    )
                )
                _uiState.update {
                    it.copy(
                        isSubmitting = false, isSubmitted = true
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isSubmitting = false, error = "Failed to submit application"
                    )
                }
            }
        }
    }
}