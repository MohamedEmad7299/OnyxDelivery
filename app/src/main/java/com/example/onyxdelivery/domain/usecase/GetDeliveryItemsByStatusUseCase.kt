package com.example.onyxdelivery.domain.usecase

import com.example.onyxdelivery.core.utils.Resource
import com.example.onyxdelivery.data.model.entity.DeliveryItemEntity
import com.example.onyxdelivery.domain.repository.CacheRepository
import javax.inject.Inject

class GetDeliveryItemsByStatusUseCase @Inject constructor(
    private val repository: CacheRepository
) {
    suspend operator fun invoke(status: String): Resource<List<DeliveryItemEntity>> {
        return try {
            val result = repository.getItemsByStatus(status)
            Resource.Success(result)
        } catch (e: Exception) {
            Resource.Error("Failed to filter items by status", e)
        }
    }
}

