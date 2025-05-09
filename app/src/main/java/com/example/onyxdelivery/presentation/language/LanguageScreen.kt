package com.example.onyxdelivery.presentation.language

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.onyxdelivery.R
import com.example.onyxdelivery.ui.theme.DeepOceanBlue
import com.example.onyxdelivery.ui.theme.MintFrost
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun LanguageScreen(
    navController: NavController,
    viewModel: LanguageViewModel = hiltViewModel()
){

    val screenState by viewModel.screenState.collectAsState()
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(Color.DarkGray)

    LanguageContent(
        screenState = screenState,
        viewModel::onChangeLanguage,
        onClickApply = {
            navController.navigate("home_screen") {
                popUpTo("language_screen") { inclusive = true }
            }
        }
    )
}


@Composable
fun LanguageContent(
    screenState: LanguageState,
    onChangeLanguageOption: (Language) -> Unit,
    onClickApply: () -> Unit
){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(28.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Choose Language",
                    fontFamily = FontFamily(Font(R.font.montserrat_semibold)),
                    color = DeepOceanBlue,
                    textAlign = TextAlign.Start,
                    fontSize = 18.sp,
                )

                Spacer(modifier = Modifier.height(24.dp))

                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    LanguageOption(
                        language = "Arabic",
                        native = "العربية",
                        icon = R.drawable.ar,
                        isSelected = screenState.isArabicSelected,
                        onClick = {
                            onChangeLanguageOption(Language.ARABIC)
                        }
                    )

                    LanguageOption(
                        language = "English",
                        native = "English",
                        icon = R.drawable.en,
                        isSelected = screenState.isEnglishSelected,
                        onClick = {
                            onChangeLanguageOption(Language.ENGLISH)
                        }
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                Button(
                    shape = RoundedCornerShape(12.dp),
                    onClick = onClickApply,
                    colors = ButtonDefaults.buttonColors(containerColor = DeepOceanBlue),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Text("Apply", color = Color.White, fontSize = 18.sp)
                }
            }
        }
    }
}

@Composable
fun LanguageOption(
    language: String,
    native: String,
    icon: Int,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor = if (isSelected) MintFrost else Color.White
    val borderColor = if (isSelected) Color.Transparent else Color.LightGray

    Box(
        modifier = Modifier
            .width(150.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(backgroundColor)
            .border(1.dp, borderColor, RoundedCornerShape(12.dp))
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = language,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(native, fontWeight = FontWeight.Bold)
                Text(language, fontSize = 12.sp, color = Color.Black)
            }
        }
    }
}