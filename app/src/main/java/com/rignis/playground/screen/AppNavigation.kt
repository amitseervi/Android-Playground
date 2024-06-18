package com.rignis.playground.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rignis.playground.screen.demolisting.DemoListingViewModel
import com.rignis.playground.screen.demolisting.SampleListScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, "sample_list_page") {
        composable("sample_list_page") {
            val demoListViewModel = hiltViewModel<DemoListingViewModel>()
            SampleListScreen(demoListViewModel) { routeId ->
                navController.navigate(routeId)
            }
        }

        composable("home_list") {
            HomeScreen(modifier = Modifier.fillMaxSize())
        }
    }
}