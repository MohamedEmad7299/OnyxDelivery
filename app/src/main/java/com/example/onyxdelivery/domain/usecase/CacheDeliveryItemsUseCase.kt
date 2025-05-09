package com.example.onyxdelivery.domain.usecase

import com.example.onyxdelivery.core.exception.CacheException
import com.example.onyxdelivery.core.utils.Resource
import com.example.onyxdelivery.data.mapper.toEntity
import com.example.onyxdelivery.data.model.dto.DeliveryBill
import com.example.onyxdelivery.domain.repository.CacheRepository
import javax.inject.Inject

class CacheDeliveryItemsUseCase @Inject constructor(
    private val repository: CacheRepository
) {
    suspend operator fun invoke(items: List<DeliveryBill>): Resource<Unit> {
        return try {
            val entities = items.map { it.toEntity() }
            repository.cacheItems(entities)
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Error("Failed to cache delivery items", e)
        }
    }
}