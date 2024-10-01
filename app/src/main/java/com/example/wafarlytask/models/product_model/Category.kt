package com.example.wafarlytask.models.product_model

data class Category(
    val id: Int,
    val image: String,
    val isActive: Boolean,
    val merchantId: String,
    val minSaleQty: Int,
    val name: String,
    val name_En: String,
    val name_Fr: String,
    val parentCategoryId: Int,
    val rank: Int
)