package com.example.onyxdelivery.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.onyxdelivery.data.local.dao.DeliveryItemDao
import com.example.onyxdelivery.data.model.entity.DeliveryItemEntity

@Database(
    entities = [DeliveryItemEntity::class],
    version = 1,
    exportSchema = false
)
abstract class OnyxDatabase : RoomDatabase() {
    abstract fun deliveryItemDao(): DeliveryItemDao
}