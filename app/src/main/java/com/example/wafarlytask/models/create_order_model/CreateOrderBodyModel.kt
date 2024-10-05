package com.example.wafarlytask.models.create_order_model

data class CreateOrderBodyModel(
    val addressId: Int,
    val customerId: String,
    val customerServiceUserId: String,
    val orderDeliveryMethod: Int,
    val orderDetails: List<OrderDetail>,
    val paymentDeliveryMethod: Int,
    val postponedDate: String,
    val priceAfterDiscountTotal: Int,
    val storeId: Int
)