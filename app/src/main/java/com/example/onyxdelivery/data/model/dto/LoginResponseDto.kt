package com.example.onyxdelivery.data.model.dto

data class LoginResponseDto(
    val Data: LoginData?,
    val Result: LoginResult
)

data class LoginData(
    val DeliveryName: String
)

data class LoginResult(
    val ErrNo: String,
    val ErrMsg: String
)