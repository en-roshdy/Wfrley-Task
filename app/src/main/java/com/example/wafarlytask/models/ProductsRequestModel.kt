package com.example.wafarlytask.models

data class ProductsRequestModel(
    val merchantId: String,
    var name: String,
    val storeId: Int
)