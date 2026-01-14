package com.loganes.lazee_tracker

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.loganes.lazee_tracker.data.repository.AuthRepositoryImpl
import com.loganes.lazee_tracker.ui.screens.login.AuthViewModel
import com.loganes.lazee_tracker.ui.theme.Lazee_TrackerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val authRepository = AuthRepositoryImpl()
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

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        contentAlignment = Alignment.Center
                    ) {
                        if (state.isLoading) {
                            Text(text = "Loading...")
                        } else if (state.isSignInSuccessful) {
                            Text(text = "LOGIN SUKSES!")
                        } else {
                            Button(onClick = {
                                Toast.makeText(this@MainActivity, "Logic Connected", Toast.LENGTH_SHORT).show()
                            }) {
                                Text(text = "Test Logic Auth")
                            }
                        }

                        state.signInError?.let { error ->
                            Text(text = "Error: $error")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Lazee_TrackerTheme {
        Greeting("Android")
    }
}