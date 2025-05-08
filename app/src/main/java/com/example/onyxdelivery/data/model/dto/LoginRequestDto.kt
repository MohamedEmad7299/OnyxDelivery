package com.example.onyxdelivery.data.model.dto

data class LoginRequestDto(
    val deliveryNo: String,
    val password: String,
    val languageNo: Int
)