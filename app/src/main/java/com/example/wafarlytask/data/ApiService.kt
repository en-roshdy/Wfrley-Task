package com.example.wafarlytask.data

import com.example.wafarlytask.models.OrdersRequestDataModel
import com.example.wafarlytask.models.ProductsRequestModel
import com.example.wafarlytask.models.orders_response.OrdersResponseModel
import com.example.wafarlytask.models.product_model.ProductModel
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("OrderCustomerservice/GetAllWithPaging")
    suspend fun getOrders(@Body dataModel: OrdersRequestDataModel): OrdersResponseModel



    @POST("OrderCustomerservice/SearchProductsForCustomer")
    suspend fun getProducts(@Body productRequestModel: ProductsRequestModel) : List<ProductModel>

}