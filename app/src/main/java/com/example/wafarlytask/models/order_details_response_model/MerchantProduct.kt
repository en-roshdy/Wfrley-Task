package com.example.wafarlytask.models.order_details_response_model

data class MerchantProduct(
    val additionalBarcode: Any,
    val alwaysAvailable: Boolean,
    val childQuantity: Any,
    val dateFrom: String,
    val dateTo: String,
    val description: String,
    val initialValue: Double,
    val isMostUsed: Boolean,
    val isOffer: Boolean,
    val maxQuantity: Int,
    val price: Int,
    val priceAfterDiscount: Int,
    val productApparence: Int,
    val productApprovalStatus: Int,
    val status: Int
)