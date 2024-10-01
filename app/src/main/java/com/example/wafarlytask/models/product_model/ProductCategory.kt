package com.example.wafarlytask.models.product_model

data class ProductCategory(
    val category: Category,
    val categoryId: Int,
    val productId: Int,
    val rank: Int
)