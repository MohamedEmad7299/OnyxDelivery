package com.example.onyxdelivery.presentation.login


data class LoginState(
    val userId: String,
    val password: String,
    val errorText: String?
)