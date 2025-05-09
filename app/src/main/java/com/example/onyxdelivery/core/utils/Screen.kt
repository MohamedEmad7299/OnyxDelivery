package com.example.onyxdelivery.core.utils

sealed class Screen(val route: String){

    data object SplashScreen : Screen("splash_screen")
    data object LoginScreen : Screen("login_screen")
    data object HomeScreen : Screen("home_screen")
}