package com.rignis.playground.screen.demolisting

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier

@Composable
fun SampleListScreen(vm: DemoListingViewModel, navigateTo: (String) -> Unit) {
    Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
        val state = vm.demoItems.collectAsState()
        val listItems = state.value
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            items(listItems.size, key = { index -> listItems[index].id }) { index ->
                ListItem(
                    headlineContent = { Text(listItems[index].title) },
                    modifier = Modifier.clickable {
                        navigateTo(listItems[index].id)
                    }
                )
            }
        }
    }
}