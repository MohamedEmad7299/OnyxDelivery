package com.example.onyxdelivery.domain.extensions

import com.example.onyxdelivery.data.model.entity.DeliveryItemEntity
import com.example.onyxdelivery.domain.model.DeliveryStatus

fun DeliveryItemEntity.status(): DeliveryStatus {
    return DeliveryStatus.from(status)
}