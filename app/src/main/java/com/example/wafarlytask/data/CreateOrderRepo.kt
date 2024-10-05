package com.example.wafarlytask.data

import com.example.wafarlytask.models.ProductsRequestModel
import com.example.wafarlytask.models.create_order_model.CreateOrderBodyModel
import com.example.wafarlytask.models.create_order_model.CreateOrderResponse
import com.example.wafarlytask.models.product_model.ProductModel
import com.example.wafarlytask.utils.Constants
import javax.inject.Inject

class CreateOrderRepo @Inject constructor(private val apiService: ApiService) {

    private val productsRequestModel = ProductsRequestModel(
        Constants.MERCHANT_ID,
        "",
        Constants.STORE_ID

        )
    suspend fun getProducts(name : String) : List<ProductModel> {
        productsRequestModel.name = name
        return apiService.getProducts(productsRequestModel)
    }


    suspend fun createOrder(orderBodyModel: CreateOrderBodyModel) : CreateOrderResponse{
        return apiService.createOrder(orderBodyModel)
    }
}