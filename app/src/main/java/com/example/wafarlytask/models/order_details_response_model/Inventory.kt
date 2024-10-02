package com.example.wafarlytask.models.order_details_response_model

data class Inventory(
    val id: Int,
    val name: String,
    val store: Store,
    val storeId: Int
)