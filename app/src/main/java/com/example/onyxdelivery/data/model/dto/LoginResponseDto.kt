package com.example.onyxdelivery.data.model.dto

data class LoginResponseDto(
    val data: LoginData?,
    val result: LoginResult
)

data class LoginData(
    val deliveryName: String
)

data class LoginResult(
    val errNo: Int,
    val errMsg: String
)