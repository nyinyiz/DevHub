package com.nyinyi.devhub.ui.screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import com.nyinyi.devhub.ui.screen.splash.SplashScreen
import com.nyinyi.devhub.ui.screen.userlist.UserListScreen

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
            UserListScreen()
        }
    }
}