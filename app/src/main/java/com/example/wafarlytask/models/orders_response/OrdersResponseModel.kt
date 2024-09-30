package com.example.wafarlytask.models.orders_response

data class OrdersResponseModel(
    val items: List<Item>,
    val itemsCount: Int
)