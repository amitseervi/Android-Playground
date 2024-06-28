/***
 * Copyright 2024 Amit Seervi
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.blue.fire.authentication

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation

fun NavGraphBuilder.authenticationNavGraph(
    route: String,
    navController: NavController,
) {
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