package com.example.onyxdelivery.domain.model

enum class DeliveryStatus(val code: String) {

    New("0"),
    Delivered("1"),
    Delivering("2"),
    Returned("3");

    companion object {
        fun from(code: String): DeliveryStatus =
            entries.firstOrNull { it.code == code } ?: New
    }
}