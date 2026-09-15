package com.endiel.jobfinder.ui.screens.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.endiel.jobfinder.data.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuthException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class AuthUiState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val isLoggedIn: Boolean = false,
    val isPasswordResetSent: Boolean = false,
    val isVerificationEmailSent: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    fun onEmailChange(value: String) {
        _uiState.update {
            it.copy(
                email = value,
                error = null
            )
        }
    }

    fun onPasswordChange(value: String) {
        _uiState.update {
            it.copy(
                password = value,
                error = null
            )
        }
    }

    fun login() {
        val state = _uiState.value

        if (state.email.isBlank() || state.password.isBlank()) {
            _uiState.update {
                it.copy(error = "Please enter your email and password.")
            }
            return
        }

        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isLoading = true,
                    error = null
                )
            }

            try {
                repository.login(
                    email = state.email,
                    password = state.password
                )

                if (!repository.isEmailVerified()) {

                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            isLoggedIn = false,
                            error = "Please verify your email before logging in"
                        )
                    }
                    return@launch
                }
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        isLoggedIn = true,
                        error = null
                    )
                }

            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = getAuthErrorMessage(e)
                    )
                }
            }
        }
    }

    fun logout() {
        repository.logout()

        _uiState.update {
            it.copy(isLoggedIn = false)
        }
    }

    fun register() {
        val state = _uiState.value

        if (state.email.isBlank() || state.password.isBlank()) {
            _uiState.update {
                it.copy(
                    error = "Please enter your email and password"
                )
            }
            return
        }
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isLoading = true,
                    error = null
                )
            }

            try {
                repository.register(
                    email = state.email,
                    password = state.password
                )

                repository.sendEmailVerification()

                _uiState.update {
                    it.copy(
                        isLoading = false,
                        isLoggedIn = true,
                        isVerificationEmailSent = true
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = getAuthErrorMessage(e)
                    )
                }
            }
        }
    }

    private fun getAuthErrorMessage(exception: Exception): String {

        if (exception !is FirebaseAuthException) {
            return "Authentification failed. Please try again"
        }

        return when(exception.errorCode) {

            "ERROR_INVALID_EMAIL" ->
                "Please enter a valid email address."

            "ERROR_INVALID_CREDENTIAL" ->
                "Incorrect email or password."

            "ERROR_USER_NOT_FOUND" ->
                "No account found with this email."

            "ERROR_EMAIL_ALREADY_IN_USE" ->
                "An account with this email already exists."

            "ERROR_WEAK_PASSWORD" ->
                "Password is too weak. Please choose a stronger password."

            "ERROR_USER_DISABLED" ->
                "This account has been disabled."

            "ERROR_TOO_MANY_REQUESTS" ->
                "Too many attempts. Please try again later."

            "ERROR_NETWORK_REQUEST_FAILED" ->
                "Network error. Please check your internet connection."

            else ->
                "Authentication failed. Please try again."

        }
    }

      fun resetPassword() {
        val email = _uiState.value.email

        if (email.isBlank()) {
            _uiState.update {
                it.copy(
                    error = "Please enter your email address"
                )
            }
            return
        }

        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isLoading = true,
                    error = null,
                    isPasswordResetSent = false
                )
            }
            try {
                repository.resetPassword(email)
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        isPasswordResetSent = true
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = e.message ?: "Password reset failed"
                    )
                }
            }
        }
      }

    fun checkAuthState(): Boolean {
        return repository.isUserLoggedIn()
    }
/*
//    fun sendEmailVerification() {
//        viewModelScope.launch {
//            _uiState.update {
//                it.copy(
//                    isLoading = true,
//                    error = null,
//                    isVerificationEmailSent = false
//                )
//            }
//            try {
//                repository.sendEmailVerification()
//                _uiState.update {
//                    it.copy(
//                        isLoading = false,
//                        isVerificationEmailSent = true
//                    )
//                }
//            } catch(e: Exception) {
//                _uiState.update {
//                    it.copy(
//                        isLoading = false,
//                        error = "Failed to send email verification"
//                    )
//                }
//        }
//        }
//    }

 */

    fun checkEmailVerification(): Boolean {
        return repository.isEmailVerified()
    }

    fun resendVerificationEmail() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isLoading = true,
                    error = null,
                    isVerificationEmailSent = false
                )
            }
            try {
               repository.sendEmailVerification()
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        isVerificationEmailSent = true
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = "Failed to send verification email. Please try again"
                    )
                }
            }
        }
    }
}