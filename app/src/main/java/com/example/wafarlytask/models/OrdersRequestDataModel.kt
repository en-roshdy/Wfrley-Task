package com.example.wafarlytask.models

data class OrdersRequestDataModel(
    val merchantId: String,
    val pageNo: Int,
    val pageSize: Int,
    val status: Int,
    val storeId: Int
)