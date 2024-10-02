package com.example.wafarlytask.models.order_details_response_model

data class ShippingAddresse(
    val cityName: String,
    val countryKey: String,
    val id: Int,
    val postCode: Any,
    val street: String,
    val telephone: String
)