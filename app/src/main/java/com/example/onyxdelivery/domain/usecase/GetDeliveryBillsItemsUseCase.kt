package com.example.onyxdelivery.domain.usecase

import com.example.onyxdelivery.core.utils.Resource
import com.example.onyxdelivery.data.model.dto.GetDeliveryBillsItemsRequestDto
import com.example.onyxdelivery.data.model.dto.GetDeliveryBillsItemsResponseDto
import com.example.onyxdelivery.domain.repository.OnyxRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetDeliveryBillsItemsUseCase @Inject constructor(
    private val repository: OnyxRepository
) {
    suspend operator fun invoke(request: GetDeliveryBillsItemsRequestDto): Resource<GetDeliveryBillsItemsResponseDto> {
        return try {
            val response = repository.getDeliveryBillsItems(request)
            if (response.isSuccessful && response.body() != null) {
                Resource.Success(response.body()!!)
            } else {
                Resource.Error("Fetching delivery items failed: ${response.message()}")
            }
        } catch (e: IOException) {
            Resource.Error("Network error", e)
        } catch (e: HttpException) {
            Resource.Error("Server error: ${e.code()}", e)
        } catch (e: Exception) {
            Resource.Error("Unexpected error fetching delivery items", e)
        }
    }
}