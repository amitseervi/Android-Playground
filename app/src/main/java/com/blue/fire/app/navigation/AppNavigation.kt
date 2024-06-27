package com.blue.fire.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.blue.fire.authentication.authenticationNavGraph
import com.blue.fire.home.homeNavGraph

@Composable
fun AppNavigation(modifier: Modifier) {
    val viewModel = hiltViewModel<AuthenticationViewModel>()
    val currentUser = viewModel.currentUser.observeAsState()
    val isAuthenticated = currentUser.value != null
    val navController = rememberNavController()
    val route = remember(isAuthenticated) {
        if (isAuthenticated) {
            "home"
        } else {
            "auth"
        }
    }
    NavHost(navController = navController, startDestination = route, modifier = modifier) {
        authenticationNavGraph("auth", navController)
        homeNavGraph("home", navController)
    }
}