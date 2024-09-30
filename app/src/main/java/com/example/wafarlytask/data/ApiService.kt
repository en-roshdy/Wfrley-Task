package com.example.wafarlytask.data

import com.example.wafarlytask.models.OrdersRequestDataModel
import com.example.wafarlytask.models.orders_response.OrdersResponseModel
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.POST

interface ApiService {

    @POST("OrderCustomerservice/GetAllWithPaging")
    suspend fun getOrders(@Body dataModel: OrdersRequestDataModel): OrdersResponseModel



}