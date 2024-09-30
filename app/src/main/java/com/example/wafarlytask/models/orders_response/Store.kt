package com.example.wafarlytask.models.orders_response

data class Store(
    val code: String,
    val id: Int,
    val inventories: List<InventoryX>,
    val isActive: Boolean,
    val name: String
)