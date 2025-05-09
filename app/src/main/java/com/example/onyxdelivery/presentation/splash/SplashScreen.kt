package com.example.onyxdelivery.presentation.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.onyxdelivery.R
import com.example.onyxdelivery.ui.theme.IcySky
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun SplashScreen(
    viewModel: SplashViewModel = hiltViewModel(),
    navController: NavController
){

    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(IcySky)
    val navigation by viewModel.navigation.collectAsState()

    SplashContent()

    when (navigation) {
        SplashNavigation.ToHome -> {
            LaunchedEffect(Unit) {
                navController.navigate("home_screen") {
                    popUpTo("splash_screen") { inclusive = true }
                }
            }
        }
        SplashNavigation.ToLogin -> {
            LaunchedEffect(Unit) {
                navController.navigate("login_screen") {
                    popUpTo("splash_screen") { inclusive = true }
                }
            }
        }
        SplashNavigation.Idle -> {
            SplashContent()
        }
    }
}

@Composable
fun SplashContent() {

    ConstraintLayout(
        modifier = Modifier
            .background(IcySky)
            .fillMaxSize()
    ){
        val logo = createRef()
        val rider = createRef()
        val buildings = createRef()

        Image(
            modifier = Modifier
                .width(220.dp)
                .height(100.dp)
                .constrainAs(logo) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Onyx Logo",
        )

        Image(
            modifier = Modifier
                .height(300.dp)
                .fillMaxWidth()
                .constrainAs(buildings) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            painter = painterResource(id = R.drawable.buildings),
            contentDescription = "Onyx Logo",
        )

        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .constrainAs(rider) {
                    bottom.linkTo(parent.bottom , 48.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            painter = painterResource(id = R.drawable.rider),
            contentDescription = "Delivery Illustration",
        )
    }
}