package com.example.wafarlytask.models.create_order_model

data class OrderDetail(
    val porductId: Int,
    val quantity: Double,
    val rowPriceAfterDiscount: Double,
    val rowTotal: Double
)