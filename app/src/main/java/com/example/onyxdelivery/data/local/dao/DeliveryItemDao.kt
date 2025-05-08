package com.example.onyxdelivery.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.onyxdelivery.data.model.entity.DeliveryItemEntity

@Dao
interface DeliveryItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<DeliveryItemEntity>)

    @Query("SELECT * FROM delivery_items")
    suspend fun getAllItems(): List<DeliveryItemEntity>

    @Query("SELECT * FROM delivery_items WHERE status = :status")
    suspend fun getItemsByStatus(status: String): List<DeliveryItemEntity>

    @Query("SELECT * FROM delivery_items WHERE mobileNo LIKE '%' || :mobile || '%'")
    suspend fun filterByMobile(mobile: String): List<DeliveryItemEntity>

    @Query("DELETE FROM delivery_items")
    suspend fun clear()
}