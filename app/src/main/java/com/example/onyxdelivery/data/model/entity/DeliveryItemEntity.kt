package com.example.onyxdelivery.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "delivery_items")
data class DeliveryItemEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val mobileNo: String,
    val date: String,
    val price: String,
    val status: String
)