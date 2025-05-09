package com.example.onyxdelivery.domain.usecase

import com.example.onyxdelivery.core.utils.Resource
import com.example.onyxdelivery.data.model.entity.DeliveryItemEntity
import com.example.onyxdelivery.domain.repository.CacheRepository
import javax.inject.Inject

class GetCachedDeliveryItemsUseCase @Inject constructor(
    private val repository: CacheRepository
) {
    suspend operator fun invoke(): Resource<List<DeliveryItemEntity>> {
        return try {
            val result = repository.getAllItems()
            Resource.Success(result)
        } catch (e: Exception) {
            Resource.Error("Failed to load cached delivery items", e)
        }
    }
}