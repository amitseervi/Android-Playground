package com.blue.fire.home.feed

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun HomeFeedRoute() {
    val homeViewModel = hiltViewModel<HomeViewModel>()
}

@Composable
fun HomePage() {

}

@Preview
@Composable
private fun HomePagePreview() {
    HomePage()
}