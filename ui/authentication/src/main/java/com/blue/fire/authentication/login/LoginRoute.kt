/***
 * Copyright 2024 Amit Seervi
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.blue.fire.authentication.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun LoginRoute(navigateToSignup: () -> Unit) {
    val loginViewModel = hiltViewModel<LoginViewModel>()
    LoginPage(loginViewModel::onLogin, navigateToSignup)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginPage(
    onLogin: (email: String, password: String) -> Unit,
    navigateToSignup: () -> Unit,
) {
    var emailInput by remember {
        mutableStateOf("")
    }
    var passwordInput by remember {
        mutableStateOf("")
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(title = { Text("Login") })
        },
    ) { padding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier =
                Modifier
                    .padding(padding)
                    .fillMaxSize(),
        ) {
            TextField(
                emailInput,
                onValueChange = { newValue -> emailInput = newValue },
                placeholder = { Text("Email") },
            )
            Spacer(modifier = Modifier.height(12.dp))
            TextField(
                passwordInput,
                onValueChange = { newValue -> passwordInput = newValue },
                placeholder = { Text("Password") },
            )
            Spacer(modifier = Modifier.height(12.dp))
            Button(onClick = {
                onLogin(emailInput, passwordInput)
            }) {
                Text("Login")
            }
            Spacer(modifier = Modifier.height(12.dp))
            ClickableText(
                text = AnnotatedString("Create new Account"),
                style = TextStyle(textDecoration = TextDecoration.Underline),
            ) {
                navigateToSignup()
            }
        }
    }
}

@Preview
@Composable
private fun LoginPagePreview() {
    LoginPage({ e, p -> }, {})
}