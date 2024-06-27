package com.blue.fire.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.blue.fire.home.feed.HomeFeedRoute

fun NavGraphBuilder.homeNavGraph(route: String, navController: NavController) {
    navigation(startDestination = "feed", route = route) {
        composable("feed") {
            HomeFeedRoute()
        }
    }
}