package com.example.onyxdelivery.data.mapper

import com.example.onyxdelivery.data.model.dto.DeliveryBill
import com.example.onyxdelivery.data.model.entity.DeliveryItemEntity

fun DeliveryBill.toEntity(): DeliveryItemEntity {

    return DeliveryItemEntity(
        mobileNo = MOBILE_NO,
        date = BILL_DATE,
        price = (BILL_AMT.toDouble() + TAX_AMT.toDouble()).toString(),
        status = DLVRY_STATUS_FLG
    )
}