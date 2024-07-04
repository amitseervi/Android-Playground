package com.rignis.home.ui.crypto

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
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
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
fun CryptoRoute() {
    val cryptoViewModel = hiltViewModel<CryptoViewModel>()
    val state = cryptoViewModel.message.collectAsState()
    CryptoPage(state, cryptoViewModel::encrypt, cryptoViewModel::decrypt)
}

@Composable
fun CryptoPage(
    messageState: State<String>, onEncrypt: (message: String) -> Unit, onDecrypt: () -> Unit
) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(messageState.value)
            Spacer(Modifier.height(12.dp))
            var messageInput by remember {
                mutableStateOf("")
            }
            TextField(value = messageInput, onValueChange = { newValue -> messageInput = newValue })
            Spacer(Modifier.height(12.dp))
            Button(onClick = {
                onEncrypt(messageInput)
            }) {
                Text("Encrypt")
            }

            Button(onClick = onDecrypt) {
                Text("Decrypt")
            }
        }
    }
}

@Preview
@Composable
private fun CryptoPagePreview() {
    val state = remember {
        mutableStateOf("")
    }
    CryptoPage(state, { message -> }, {})
}