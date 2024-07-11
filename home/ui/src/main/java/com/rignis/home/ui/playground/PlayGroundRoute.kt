package com.rignis.home.ui.playground

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun PlayGroundRoute() {
    val viewModel = hiltViewModel<PlayGroundViewModel>()
    val progressState = viewModel.progress.collectAsStateWithLifecycle()
    PlaygroundPage(progressState,viewModel::onButtonPress)
}

@Composable
fun PlaygroundPage(progressState: State<Int>, onButtonPress: () -> Unit) {
    Scaffold {
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Progress = ${progressState.value}")
            Button(onButtonPress) {
                Text("Press Me!!")
            }
        }
    }
}