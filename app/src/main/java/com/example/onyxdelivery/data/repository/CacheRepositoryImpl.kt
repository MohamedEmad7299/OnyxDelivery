package com.example.onyxdelivery.data.repository

import com.example.onyxdelivery.core.exception.CacheException
import com.example.onyxdelivery.core.exception.DatabaseException
import com.example.onyxdelivery.data.local.dao.DeliveryItemDao
import com.example.onyxdelivery.data.model.entity.DeliveryItemEntity
import com.example.onyxdelivery.domain.repository.CacheRepository
import javax.inject.Inject

class CacheRepositoryImpl @Inject constructor(
    private val dao: DeliveryItemDao
) : CacheRepository {

    override suspend fun cacheItems(items: List<DeliveryItemEntity>) {
        try {
            dao.clear()
            dao.insertAll(items)
        } catch (e: Exception) {
            throw CacheException()
        }
    }

    override suspend fun getAllItems(): List<DeliveryItemEntity> {
        return try {
            dao.getAllItems()
        } catch (e: Exception) {
            throw DatabaseException()
        }
    }

    override suspend fun getItemsByStatus(status: String): List<DeliveryItemEntity> {
        return try {
            dao.getItemsByStatus(status)
        } catch (e: Exception) {
            throw DatabaseException()
        }
    }

    override suspend fun filterByMobile(mobile: String): List<DeliveryItemEntity> {
        return try {
            dao.filterByMobile(mobile)
        } catch (e: Exception) {
            throw DatabaseException()
        }
    }
}