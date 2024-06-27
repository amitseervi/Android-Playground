package com.blue.fire.authentication

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation

fun NavGraphBuilder.authenticationNavGraph(route: String, navController: NavController) {
    navigation(startDestination = "login", route = route) {
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
}