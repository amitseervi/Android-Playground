package com.blue.fire.authentication.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
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
fun SignupRoute(navigateUp: () -> Unit) {
    val signupViewModel = hiltViewModel<SignupViewModel>()
    SignupPage(signupViewModel::onSignup, navigateUp)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupPage(
    onSignup: (user: String, email: String, password: String) -> Unit,
    navigateUp: () -> Unit
) {
    var userName by remember {
        mutableStateOf("")
    }
    var emailInput by remember {
        mutableStateOf("")
    }
    var passwordInput by remember {
        mutableStateOf("")
    }
    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(title = { Text("Signup") }, navigationIcon = {
                IconButton(navigateUp) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                }
            })
        }) { padding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
        ) {
            TextField(
                userName,
                onValueChange = { newValue -> userName = newValue },
                placeholder = { Text("DisplayName") })
            Spacer(modifier = Modifier.height(12.dp))
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
                onSignup(userName, emailInput, passwordInput)
            }) {
                Text("Signup")
            }
        }
    }
}

@Preview
@Composable
private fun SignupPagePreview() {
    SignupPage({ u, e, p -> }, {})
}