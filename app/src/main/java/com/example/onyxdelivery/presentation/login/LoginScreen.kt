package com.example.onyxdelivery.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.onyxdelivery.R
import com.example.onyxdelivery.core.utils.Resource
import com.example.onyxdelivery.data.model.dto.LoginResponseDto
import com.example.onyxdelivery.ui.theme.CloudBlue
import com.example.onyxdelivery.ui.theme.DeepOceanBlue
import com.example.onyxdelivery.ui.theme.White
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel(),
    onLoginSuccess: () -> Unit = { navController.navigate("home_screen") }
) {

    val screenState by viewModel.screenState.collectAsState()

    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setStatusBarColor(
            color = Color.Transparent,
            darkIcons = true
        )
    }

    val loginState by viewModel.loginResultState.collectAsState()

    LoginContent(
        screenState = screenState,
        onLoginClick = { userId, password ->
            viewModel.login(userId, password)
        },
        loginState = loginState,
        onChangePassword = viewModel::onChangePassword,
        onChangeUserId = viewModel::onChangeUserID,
        onChangeErrorMessage = viewModel::onChangeErrorMessage
    )

    if (loginState is Resource.Success) {
        LaunchedEffect(Unit) {
            onLoginSuccess()
        }
    }
}

@Composable
fun LoginContent(
    onChangeErrorMessage: (String?) -> Unit,
    onChangeUserId: (String) -> Unit,
    onChangePassword: (String) -> Unit,
    screenState: LoginState,
    onLoginClick: (deliveryNo: String, password: String) -> Unit,
    loginState: Resource<LoginResponseDto>
) {


    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {

        val (logo,
            circle,
            language,
            welcomeMessage,
            logBackMessage,
            userTextField,
            passTextField,
            showMore,
            loginButton,
            car,
            errorMessage) = createRefs()


        Image(
            modifier = Modifier
                .width(170.dp)
                .height(100.dp)
                .constrainAs(logo) {
                    top.linkTo(parent.top, 72.dp)
                    start.linkTo(parent.start, 26.dp)
                },
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Onyx Logo",
        )

        Image(
            modifier = Modifier
                .width(160.dp)
                .height(160.dp)
                .constrainAs(circle) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                },
            painter = painterResource(id = R.drawable.circle),
            contentDescription = "",
        )

        Text(
            modifier = Modifier.constrainAs(welcomeMessage) {
                top.linkTo(logo.bottom, margin = 64.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            text = "Welcome Back!",
            color = DeepOceanBlue,
            fontFamily = FontFamily(Font(R.font.montserrat_semibold)),
            fontSize = 28.sp,
        )

        Text(
            modifier = Modifier.constrainAs(logBackMessage) {
                top.linkTo(welcomeMessage.bottom, margin = 12.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            text = "Log back into your account",
            color = DeepOceanBlue,
            fontFamily = FontFamily(Font(R.font.montserrat_medium)),
            fontSize = 12.sp,
        )

        IconButton(
            onClick = { /* TODO: Handle click */ },
            modifier = Modifier
                .size(48.dp)
                .constrainAs(language) {
                    top.linkTo(parent.top, margin = 72.dp)
                    end.linkTo(parent.end, margin = 16.dp)
                }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.language),
                contentDescription = "Language",
                tint = Color.White
            )
        }

        TextField(
            value = screenState.userId,
            onValueChange = {
                onChangeUserId(it)
                onChangeErrorMessage(null)
            },
            placeholder = {
                Text(
                    text = "User ID",
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.montserrat_regular)),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            },
            singleLine = true,
            textStyle = TextStyle(
                textAlign = TextAlign.Center,
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.montserrat_regular))
            ),
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .constrainAs(userTextField) {
                    top.linkTo(logBackMessage.bottom, 58.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .height(56.dp)
                .clip(RoundedCornerShape(28.dp))
                .background(CloudBlue),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = CloudBlue,
                unfocusedContainerColor = CloudBlue,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )

        TextField(
            value = screenState.password,
            onValueChange = {
                onChangePassword(it)
                onChangeErrorMessage(null)
            },
            visualTransformation = PasswordVisualTransformation(),
            placeholder = {
                Text(
                    text = "Password",
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.montserrat_regular)),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            },
            singleLine = true,
            textStyle = TextStyle(
                textAlign = TextAlign.Center,
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.montserrat_regular))
            ),
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .constrainAs(passTextField) {
                    top.linkTo(userTextField.bottom, 8.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .height(56.dp)
                .clip(RoundedCornerShape(28.dp))
                .background(CloudBlue),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = CloudBlue,
                unfocusedContainerColor = CloudBlue,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )

        if (loginState is Resource.Error) {
            onChangeErrorMessage(loginState.message)
        }

        screenState.errorText?.let { msg ->
            Text(
                text = msg,
                color = Color.Red,
                fontSize = 12.sp,
                modifier = Modifier
                    .constrainAs(errorMessage) {
                        top.linkTo(passTextField.bottom, 8.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )
        }

        Text(
            modifier = Modifier.constrainAs(showMore) {
                top.linkTo(passTextField.bottom, margin = 28.dp)
                end.linkTo(parent.end , 16.dp)
            },
            text = "Show More",
            color = DeepOceanBlue,
            fontFamily = FontFamily(Font(R.font.montserrat_semibold)),
            fontSize = 14.sp,
        )

        Button(
            onClick = {
                if (screenState.userId.isBlank() || screenState.password.isBlank()) {
                    onChangeErrorMessage("Please fill in both fields")
                } else {
                    onLoginClick(screenState.userId, screenState.password)
                }
            },
            modifier = Modifier
                .constrainAs(loginButton){
                    top.linkTo(showMore.bottom, 24.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .fillMaxWidth(0.9f)
                .height(56.dp)
                .clip(RoundedCornerShape(28.dp)),
            colors = ButtonDefaults.buttonColors(
                containerColor = DeepOceanBlue,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(28.dp),
            contentPadding = PaddingValues()
        ) {

            if (loginState is Resource.Loading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(32.dp),
                    color = Color.White,
                )

            } else {

                Text(
                    text = "Log in",
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.montserrat_medium)),
                    textAlign = TextAlign.Center
                )
            }
        }

        Image(
            modifier = Modifier
                .fillMaxWidth()
                .size(160.dp)
                .constrainAs(car) {
                    top.linkTo(loginButton.bottom, 32.dp)
                },
            painter = painterResource(id = R.drawable.car),
            contentDescription = "Onyx Logo",
        )
    }
}