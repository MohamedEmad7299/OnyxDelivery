package com.example.onyxdelivery.domain.repository

import com.example.onyxdelivery.data.model.dto.GetDeliveryBillsItemsRequestDto
import com.example.onyxdelivery.data.model.dto.GetDeliveryBillsItemsResponseDto
import com.example.onyxdelivery.data.model.dto.LoginRequestDto
import com.example.onyxdelivery.data.model.dto.LoginResponseDto
import retrofit2.Response

interface OnyxRepository {

    suspend fun checkDeliveryLogin(request: LoginRequestDto): Response<LoginResponseDto>
    suspend fun getDeliveryBillsItems(request: GetDeliveryBillsItemsRequestDto): Response<GetDeliveryBillsItemsResponseDto>
}