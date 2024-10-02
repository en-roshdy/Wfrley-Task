package com.example.wafarlytask.models.order_details_response_model

data class ProductInvetory(
    val id: Int,
    val inventoryId: Int,
    val price: Int,
    val priceAfterDiscount: Int,
    val productId: Int,
    val quantity: Int,
    val salableQuantity: Double,
    val status: Boolean
)