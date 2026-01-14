package com.loganes.lazee_tracker.ui.screens.login 

data class SignInState(
    val isSignInSuccessful: Boolean = false,
    val signInError: String? = null,
    val isLoading: Boolean = false
)