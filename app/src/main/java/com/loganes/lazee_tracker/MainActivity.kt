package com.loganes.lazee_tracker

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

// IMPORT DARI LIBRARY GOOGLE (Sekarang akan terbaca setelah langkah 1 dilakukan)
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.common.api.ApiException

import com.loganes.lazee_tracker.data.repository.AuthRepositoryImpl
import com.loganes.lazee_tracker.ui.screens.login.AuthViewModel
import com.loganes.lazee_tracker.ui.screens.login.LoginScreen
import com.loganes.lazee_tracker.ui.theme.Lazee_TrackerTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val authRepository = AuthRepositoryImpl()
        // Ini butuh library play-services-auth
        val oneTapClient = Identity.getSignInClient(this)

        val factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return AuthViewModel(authRepository) as T
            }
        }
        val viewModel = ViewModelProvider(this, factory)[AuthViewModel::class.java]

        setContent {
            Lazee_TrackerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val state by viewModel.state.collectAsState()
                    val scope = rememberCoroutineScope()

                    val launcher = rememberLauncherForActivityResult(
                        contract = ActivityResultContracts.StartIntentSenderForResult(),
                        onResult = { result ->
                            if (result.resultCode == RESULT_OK) {
                                try {
                                    val credential = oneTapClient.getSignInCredentialFromIntent(result.data)
                                    val googleIdToken = credential.googleIdToken
                                    if (googleIdToken != null) {
                                        viewModel.onSignInResult(googleIdToken)
                                    }
                                } catch (e: ApiException) {
                                    e.printStackTrace()
                                }
                            }
                        }
                    )

                    LaunchedEffect(key1 = state.isSignInSuccessful) {
                        if (state.isSignInSuccessful) {
                            Toast.makeText(applicationContext, "Login Successful!", Toast.LENGTH_LONG).show()
                        }
                    }

                    LoginScreen(
                        state = state,
                        onSignInClick = {
                            scope.launch {
                                try {
                                    // Kode Request Login
                                    val signInRequest = BeginSignInRequest.builder()
                                        .setGoogleIdTokenRequestOptions(
                                            BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                                                .setSupported(true)
                                                .setServerClientId(getString(R.string.default_web_client_id))
                                                .setFilterByAuthorizedAccounts(false)
                                                .build()
                                        )
                                        .setAutoSelectEnabled(true)
                                        .build()

                                    oneTapClient.beginSignIn(signInRequest)
                                        .addOnSuccessListener(this@MainActivity) { result ->
                                            try {
                                                launcher.launch(
                                                    IntentSenderRequest.Builder(result.pendingIntent).build()
                                                )
                                            } catch (e: Exception) {
                                                e.printStackTrace()
                                            }
                                        }
                                        .addOnFailureListener(this@MainActivity) { e ->
                                            Toast.makeText(applicationContext, e.localizedMessage, Toast.LENGTH_LONG).show()
                                        }
                                } catch (e: Exception) {
                                    e.printStackTrace()
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(text = "Hello $name!", modifier = modifier)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Lazee_TrackerTheme { Greeting("Android") }
}