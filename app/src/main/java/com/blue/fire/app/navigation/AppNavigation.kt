package com.blue.fire.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.blue.fire.home.HomeRoute

@Composable
fun AppNavigation(modifier: Modifier) {
    val viewModel = hiltViewModel<AuthenticationViewModel>()
    val currentUser = viewModel.currentUser.observeAsState()
    val isAuthenticated = currentUser.value != null
    val navController = rememberNavController()
    val route = remember(isAuthenticated) {
        if (isAuthenticated) {
            "authenticated"
        } else {
            "auth"
        }
    }
    NavHost(navController = navController, startDestination = route, modifier = modifier) {
        navigation(startDestination = "login", route = "auth") {
            composable("login") {
                com.blue.fire.authentication.login.LoginRoute(navigateToSignup = {
                    navController.navigate("signup")
                })
            }

            composable("signup") {
                com.blue.fire.authentication.signup.SignupRoute(navigateUp = {
                    navController.navigateUp()
                })
            }
        }
        navigation(startDestination = "home", route = "authenticated") {
            composable("home") {
                HomeRoute()
            }
        }
    }
}