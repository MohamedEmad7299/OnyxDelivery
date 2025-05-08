package com.example.onyxdelivery.domain.model

enum class DeliveryStatus(val code: String) {

    New("0"),
    Delivered("1"),
    PartialReturn("2"),
    Return("3");

    companion object {
        fun from(code: String): DeliveryStatus =
            entries.firstOrNull { it.code == code } ?: New
    }
}