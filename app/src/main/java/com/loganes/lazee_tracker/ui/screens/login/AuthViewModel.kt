package com.loganes.lazee_tracker.ui.screens.login // Package disesuaikan

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loganes.lazee_tracker.domain.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AuthViewModel(
    private val repository: AuthRepository
) : ViewModel() {

    private val _state = MutableStateFlow(SignInState())
    val state = _state.asStateFlow()

    // Fungsi pemanggilan id token from ggl
    fun onSignInResult(idToken: String) {
        _state.update { it.copy(isLoading = true) }
        
        viewModelScope.launch {
            val result = repository.signInWithGoogle(idToken)
            
            result.onSuccess {
                _state.update { state ->
                    state.copy(
                        isSignInSuccessful = true,
                        isLoading = false,
                        signInError = null
                    )
                }
            }
            .onFailure { error ->
                _state.update { state ->
                    state.copy(
                        isSignInSuccessful = false,
                        isLoading = false,
                        signInError = error.message
                    )
                }
            }
        }
    }

    fun resetState() {
        _state.update { SignInState() }
    }
}