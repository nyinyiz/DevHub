package com.nyinyi.devhub.ui.screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navOptions
import com.nyinyi.devhub.ui.screen.splash.SplashScreen
import com.nyinyi.devhub.ui.screen.userDetail.UserDetailScreen
import com.nyinyi.devhub.ui.screen.userlist.UserListScreen
import com.nyinyi.devhub.ui.screen.webview.FullScreenWebView
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun SetUpNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screens.SplashScreen.route
    ) {
        composable(route = Screens.SplashScreen.route) {
            SplashScreen {
                navController.navigate(
                    route = Screens.UserListScreen.route,
                    navOptions = navOptions {
                        popUpTo(Screens.SplashScreen.route) {
                            inclusive = true
                        }
                    }
                )
            }
        }
        composable(route = Screens.UserListScreen.route) {
            UserListScreen(
                onUserClick = { userName ->
                    navController.navigate(Screens.UserDetailScreen.route + "/$userName")
                }
            )
        }
        composable(
            route = Screens.UserDetailScreen.route + "/{user_name}",
            arguments = listOf(
                navArgument("user_name") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val name =
                backStackEntry.arguments?.getString("user_name") ?: ""
            UserDetailScreen(
                userName = name,
                onBack = { navController.popBackStack() },
                onClickWebView = { url ->
                    val encodedUrl = URLEncoder.encode(url, StandardCharsets.UTF_8.toString())
                    navController.navigate(Screens.WebViewScreen.route + "/$encodedUrl")
                }
            )
        }

        composable(
            route = Screens.WebViewScreen.route + "/{url}",
            arguments = listOf(
                navArgument("url") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val url = backStackEntry.arguments?.getString("url") ?: ""
            FullScreenWebView(
                url = url,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
