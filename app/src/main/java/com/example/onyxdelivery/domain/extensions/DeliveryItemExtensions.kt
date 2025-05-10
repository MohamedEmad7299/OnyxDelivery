package com.example.onyxdelivery.domain.extensions

import androidx.compose.ui.graphics.Color
import com.example.onyxdelivery.data.model.entity.DeliveryItemEntity
import com.example.onyxdelivery.ui.theme.DeepOceanBlue
import com.example.onyxdelivery.ui.theme.ElectricGreen
import com.example.onyxdelivery.ui.theme.Firestorm
import com.example.onyxdelivery.ui.theme.SteelGray

fun DeliveryItemEntity.mapStatus(): String {
    return when (status) {
        "0" -> "New"
        "1" -> "Delivered"
        "2" -> "Delivering"
        "3" -> "Returned"
        else -> "Unknown"
    }
}

fun DeliveryItemEntity.statusToColor(): Color {
    return when (status) {
        "0" -> ElectricGreen
        "1" -> SteelGray
        "2" -> DeepOceanBlue
        "3" -> Firestorm
        else -> Color.Black
    }
}