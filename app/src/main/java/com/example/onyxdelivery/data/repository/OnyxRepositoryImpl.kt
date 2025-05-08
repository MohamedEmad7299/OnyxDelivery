package com.example.onyxdelivery.data.repository

import com.example.onyxdelivery.data.model.dto.GetDeliveryBillsItemsRequestDto
import com.example.onyxdelivery.data.model.dto.GetDeliveryBillsItemsResponseDto
import com.example.onyxdelivery.data.model.dto.LoginRequestDto
import com.example.onyxdelivery.data.model.dto.LoginResponseDto
import com.example.onyxdelivery.domain.repository.OnyxRepository
import retrofit2.Response

class OnyxRepositoryImpl : OnyxRepository {

    override suspend fun checkDeliveryLogin(request: LoginRequestDto): Response<LoginResponseDto> {
        TODO("Not yet implemented")
    }

    override suspend fun getDeliveryBillsItems(request: GetDeliveryBillsItemsRequestDto): Response<List<GetDeliveryBillsItemsResponseDto>> {
        TODO("Not yet implemented")
    }
}