package com.example.onyxdelivery.presentation.navigation

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.onyxdelivery.core.utils.Screen
import com.example.onyxdelivery.presentation.home.HomeScreen
import com.example.onyxdelivery.presentation.language.LanguageScreen
import com.example.onyxdelivery.presentation.login.LoginScreen
import com.example.onyxdelivery.presentation.session.SessionViewModel
import com.example.onyxdelivery.presentation.splash.SplashScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun OnyxNavHost(
    sessionViewModel: SessionViewModel
){

    val navController = rememberNavController()

    val sessionExpired by sessionViewModel.sessionExpired.collectAsState()

    if (sessionExpired) {
        LaunchedEffect(Unit) {
            navController.navigate("login_screen") {
                popUpTo(0) { inclusive = true }
            }
            sessionViewModel.resetSession()
        }
    }

    Scaffold{
        NavHost(
            navController = navController,
            startDestination = Screen.SplashScreen.route
        ){
            composable(Screen.SplashScreen.route){ SplashScreen(navController = navController) }
            composable(Screen.LoginScreen.route){ LoginScreen(navController = navController) }
            composable(Screen.HomeScreen.route){ HomeScreen(navController = navController) }
            composable(Screen.LanguageScreen.route){ LanguageScreen(navController = navController) }
        }
    }
}