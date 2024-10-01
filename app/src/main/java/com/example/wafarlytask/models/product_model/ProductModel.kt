package com.example.wafarlytask.models.product_model

data class ProductModel(
    val additionalBarcode: Any,
    val alwaysAvailable: Boolean,
    val barCode: String,
    val childQuantity: Any,
    val id: Int,
    val image: String,
    val isAvailable: Boolean,
    val merchantProducts: List<MerchantProduct>,
    val name: String,
    val price: Double,
    val priceAfterDiscount: Double,
    val productCategories: List<ProductCategory>,
    val productInvetories: List<ProductInvetory>,
    val productType: Int,
    val salableQuantity: Double,
    val sku: String,
    val subCategoryId: Int
)