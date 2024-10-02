package com.example.wafarlytask.utils

import com.example.wafarlytask.models.product_model.ProductModel

interface OrderProductsListener {

    fun increaseQuantity(productModel: ProductModel)

    fun decreaseQuantity(productModel: ProductModel)

    fun addToOrder(productModel: ProductModel)

    fun removeFromOrder(productModel: ProductModel)
}