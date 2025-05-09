package com.example.onyxdelivery.presentation.splash


sealed class SplashNavigation {
    data object ToHome : SplashNavigation()
    data object ToLogin : SplashNavigation()
    data object Idle : SplashNavigation()
}