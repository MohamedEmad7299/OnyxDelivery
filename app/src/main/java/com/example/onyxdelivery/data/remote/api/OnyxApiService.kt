package com.example.onyxdelivery.data.remote.api

import com.example.onyxdelivery.data.model.dto.GetDeliveryBillsItemsRequestDto
import com.example.onyxdelivery.data.model.dto.GetDeliveryBillsItemsResponseDto
import com.example.onyxdelivery.data.model.dto.LoginRequestDto
import com.example.onyxdelivery.data.model.dto.LoginResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface OnyxApiService {

    @POST("CheckDeliveryLogin")
    suspend fun checkDeliveryLogin(
        @Body request: LoginRequestDto
    ): Response<LoginResponseDto>

    @POST("GetDeliveryBillsItems")
    suspend fun getDeliveryBillsItems(
        @Body request: GetDeliveryBillsItemsRequestDto
    ): Response<GetDeliveryBillsItemsResponseDto>
}