package com.blue.fire.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.blue.fire.app.home.HomeRoute
import com.blue.fire.app.login.LoginRoute
import com.blue.fire.app.signup.SignupRoute

@Composable
fun AppNavigation(isAuthenticated: Boolean) {
    val navController = rememberNavController()
    val route = remember(isAuthenticated) {
        if (isAuthenticated) {
            "authenticated"
        } else {
            "auth"
        }
    }
    NavHost(navController, "auth") {
        navigation(startDestination = "login", route = route) {
            composable("login") {
                LoginRoute()
            }

            composable("signup") {
                SignupRoute()
            }
        }
        navigation(startDestination = "home", route = route) {
            composable("home") {
                HomeRoute()
            }
        }
    }
}