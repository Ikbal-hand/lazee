package com.loganes.lazee_tracker.domain.repository

import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    val currentUser: Flow<String?>

    suspend fun signInWithGoogle(idToken: String): Result<String>

    suspend fun signOut()
}