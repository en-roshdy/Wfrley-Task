package com.example.wafarlytask.models.orders_response

data class Inventory(
    val id: Int,
    val name: String,
    val store: Store,
    val storeId: Int
)