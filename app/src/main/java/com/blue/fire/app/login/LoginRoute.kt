package com.blue.fire.app.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun LoginRoute() {
    val loginViewModel = hiltViewModel<LoginViewModel>()

}

@Composable
fun LoginPage(onLogin: (email: String, password: String) -> Unit) {
    var emailInput by remember {
        mutableStateOf("")
    }
    var passwordInput by remember {
        mutableStateOf("")
    }
    Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
        ) {
            TextField(
                emailInput,
                onValueChange = { newValue -> emailInput = newValue },
                placeholder = { Text("Email") })
            Spacer(modifier = Modifier.height(12.dp))
            TextField(
                passwordInput,
                onValueChange = { newValue -> passwordInput = newValue },
                placeholder = { Text("Password") })
            Spacer(modifier = Modifier.height(12.dp))
            Button(onClick = {
                onLogin(emailInput, passwordInput)
            }) {
                Text("Login")
            }
        }
    }
}

@Preview
@Composable
private fun LoginPagePreview() {
    LoginPage { e, p -> }
}