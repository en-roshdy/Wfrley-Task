package com.example.wafarlytask.data

import com.example.wafarlytask.models.OrdersRequestDataModel
import com.example.wafarlytask.models.ProductsRequestModel
import com.example.wafarlytask.models.order_details_response_model.OrderDetailsResponse
import com.example.wafarlytask.models.orders_response.OrdersResponseModel
import com.example.wafarlytask.utils.Constants
import javax.inject.Inject

class OrdersRepo @Inject constructor(private val apiService: ApiService) {



    suspend fun getAllOrders(page: Int): OrdersResponseModel {
        return apiService.getOrders(
            OrdersRequestDataModel(
                Constants.MERCHANT_ID,
                page,
                Constants.ORDERS_PAGE_SIZE,
                Constants.STATUS,
                Constants.STORE_ID

            )
        )
    }

    suspend fun getOrderById(orderId : Int) : OrderDetailsResponse{
        return apiService.getOrderDetails(orderId,Constants.MERCHANT_ID)
    }




}