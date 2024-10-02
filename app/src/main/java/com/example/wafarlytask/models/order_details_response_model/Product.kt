package com.example.wafarlytask.models.order_details_response_model

data class Product(
    val additionalBarcode: Any,
    val alwaysAvailable: Boolean,
    val barCode: String,
    val childQuantity: Any,
    val id: Int,
    val image: String,
    val isAvailable: Boolean,
    val merchantProducts: List<MerchantProduct>,
    val name: String,
    val price: Int,
    val priceAfterDiscount: Int,
    val productCategories: List<Any>,
    val productInvetories: List<ProductInvetory>,
    val productType: Int,
    val salableQuantity: Double,
    val sku: String,
    val subCategoryId: Int
)