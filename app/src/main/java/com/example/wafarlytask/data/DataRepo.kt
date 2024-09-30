package com.example.wafarlytask.data

import com.example.wafarlytask.models.OrdersRequestDataModel
import com.example.wafarlytask.models.orders_response.OrdersResponseModel
import com.example.wafarlytask.utils.Constants
import javax.inject.Inject

class DataRepo @Inject constructor(private val apiService: ApiService) {

    suspend fun getAllOrders(page: Int): OrdersResponseModel {
        return apiService.getOrders(
            OrdersRequestDataModel(
                Constants.MERCHANT_ID,
                page,
                10,
                Constants.STATUS,
                Constants.STORE_ID

            )
        )
    }

}