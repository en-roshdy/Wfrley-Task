package com.example.wafarlytask.models.order_details_response_model

data class OrderDetail(
    val id: Int,
    val product: Product,
    val productCustomizationId: Any,
    val productCustomizations: Any,
    val quantity: Double,
    val rowPriceAfterDiscount: Int,
    val rowTotal: Double,
    val syncSucceed: Boolean
)