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
package com.rignis.home.ui

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.rignis.home.ui.crypto.CryptoRoute
import com.rignis.home.ui.feed.HomeFeedRoute
import com.rignis.home.ui.playground.PlayGroundRoute

fun NavGraphBuilder.homeNavGraph(
    route: String,
    navController: NavController,
) {
    navigation(startDestination = "feed", route = route) {
        composable("feed") {
            HomeFeedRoute(navigateToCrypto = {
                navController.navigate("demo-crypto")
            },navigateToPlayground = {
                navController.navigate("demo-playground")
            })
        }

        composable("demo-crypto") {
            CryptoRoute()
        }

        composable("demo-playground") {
            PlayGroundRoute()
        }

    }
}