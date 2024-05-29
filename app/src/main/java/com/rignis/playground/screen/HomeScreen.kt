package com.rignis.playground.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.rignis.playground.MainViewModel
import com.rignis.playground.domain.model.FeedItem
import com.rignis.playground.state.MainScreenState

@Composable
fun HomeScreen(modifier: Modifier) {
    val viewModel: MainViewModel = hiltViewModel<MainViewModel>()
    val state by viewModel.state.collectAsState()
    HomeScreenContent(modifier, state)
}

@Composable
private fun HomeScreenContent(modifier: Modifier, state: MainScreenState) {
    when (state) {
        is MainScreenState.HasData -> WithDataScreen(modifier, state)
        is MainScreenState.NoData -> NoDataScreen(modifier, state)
    }
}

@Composable
private fun NoDataScreen(modifier: Modifier, state: MainScreenState.NoData) {
    if (state.loading) {
        Box(modifier) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    } else {
        Box(modifier) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = state.error ?: "Unexpected error"
            )
        }
    }
}

@Composable
private fun WithDataScreen(modifier: Modifier, state: MainScreenState.HasData) {
    Box(modifier = modifier) {
        if (state.loading) {
            Box(modifier) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.TopCenter))
            }
        }
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(
                state.data.size,
                key = { index -> state.data[index].id },
                contentType = { 1 }) { index ->
                val item = state.data[index]
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text(item.title)
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(item.description)
                        Spacer(modifier = Modifier.height(12.dp))
                        AsyncImage(
                            item.image,
                            contentDescription = "Content Image",
                            modifier = Modifier.aspectRatio(0.5f)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun MyListItemCard(modifier: Modifier, item: FeedItem) {
    Card(modifier = modifier.fillMaxWidth()) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(item.title)
            Spacer(modifier = Modifier.height(12.dp))
            Text(item.description)
            Spacer(modifier = Modifier.height(12.dp))
            AsyncImage(
                item.image,
                contentDescription = "Content Image",
                modifier = Modifier.aspectRatio(2f)
            )
        }
    }
}

@Preview
@Composable
private fun PreviewScreen() {
    HomeScreenContent(modifier = Modifier.fillMaxSize(), MainScreenState.NoData(loading = true))
}

@Preview
@Composable
private fun PreviewListCardItem() {
    MyListItemCard(
        modifier = Modifier.fillMaxWidth(),
        FeedItem(
            "123",
            "Hello world",
            "https://picsum.photos/id/284/800/400",
            "test description"
        )
    )
}