package com.example.wafarlytask.models.product_model

data class ProductInvetory(
    val id: Int,
    val inventoryId: Int,
    val price: Double,
    val priceAfterDiscount: Double,
    val productId: Int,
    val quantity: Double,
    val salableQuantity: Double,
    val status: Boolean
)