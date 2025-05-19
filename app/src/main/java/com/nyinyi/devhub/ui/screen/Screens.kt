package com.nyinyi.devhub.ui.screen

sealed class Screens(
    val route: String
) {
    object SplashScreen : Screens("splash_screen")
    object UserListScreen : Screens("user_list_screen")
    object UserDetailScreen : Screens("user_detail_screen")
    object WebViewScreen : Screens("web_view_screen")
}