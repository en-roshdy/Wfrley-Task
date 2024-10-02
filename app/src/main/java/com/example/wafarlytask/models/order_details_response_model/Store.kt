package com.example.wafarlytask.models.order_details_response_model

data class Store(
    val code: String,
    val id: Int,
    val inventories: List<InventoryX>,
    val isActive: Boolean,
    val name: String
)