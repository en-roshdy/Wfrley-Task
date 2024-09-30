package com.example.wafarlytask.models.orders_response

data class ShippingAddresse(
    val cityName: String,
    val countryKey: String,
    val id: Int,
    val postCode: Any,
    val street: String,
    val telephone: String
)