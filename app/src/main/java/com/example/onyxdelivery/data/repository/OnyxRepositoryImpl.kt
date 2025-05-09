package com.example.onyxdelivery.data.repository

import com.example.onyxdelivery.core.exception.NetworkException
import com.example.onyxdelivery.core.exception.OnyxException
import com.example.onyxdelivery.data.model.dto.GetDeliveryBillsItemsRequestDto
import com.example.onyxdelivery.data.model.dto.GetDeliveryBillsItemsResponseDto
import com.example.onyxdelivery.data.model.dto.LoginRequestDto
import com.example.onyxdelivery.data.model.dto.LoginResponseDto
import com.example.onyxdelivery.data.remote.api.OnyxApiService
import com.example.onyxdelivery.domain.repository.OnyxRepository
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class OnyxRepositoryImpl @Inject constructor(
    private val onyxApiService: OnyxApiService
) : OnyxRepository {

    override suspend fun checkDeliveryLogin(request: LoginRequestDto): Response<LoginResponseDto> {
        return try {
            onyxApiService.checkDeliveryLogin(request)
        } catch (e: IOException) {
            throw NetworkException()
        } catch (e: HttpException) {
            throw NetworkException("Server error: ${e.code()}")
        } catch (e: Exception) {
            e.printStackTrace()
            throw OnyxException("Unexpected error occurred during login")
        }
    }

    override suspend fun getDeliveryBillsItems(request: GetDeliveryBillsItemsRequestDto): Response<GetDeliveryBillsItemsResponseDto> {
        return try {
            onyxApiService.getDeliveryBillsItems(request)
        } catch (e: IOException) {
            throw NetworkException()
        } catch (e: HttpException) {
            throw NetworkException("Server error: ${e.code()}")
        } catch (e: Exception) {
            throw OnyxException("Unexpected error occurred while fetching delivery bills")
        }
    }
}