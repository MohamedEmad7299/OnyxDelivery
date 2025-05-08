package com.example.onyxdelivery.domain.repository

import com.example.onyxdelivery.data.model.entity.DeliveryItemEntity

interface CacheRepository {

    suspend fun cacheItems(items: List<DeliveryItemEntity>)
    suspend fun getAllItems(): List<DeliveryItemEntity>
    suspend fun getItemsByStatus(status: String): List<DeliveryItemEntity>
    suspend fun filterByMobile(mobile: String): List<DeliveryItemEntity>
}