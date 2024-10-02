package com.example.wafarlytask.data

import com.example.wafarlytask.models.OrdersRequestDataModel
import com.example.wafarlytask.models.ProductsRequestModel
import com.example.wafarlytask.models.order_details_response_model.OrderDetailsResponse
import com.example.wafarlytask.models.orders_response.OrdersResponseModel
import com.example.wafarlytask.models.product_model.ProductModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @POST("OrderCustomerservice/GetAllWithPaging")
    suspend fun getOrders(@Body dataModel: OrdersRequestDataModel): OrdersResponseModel


    @GET("OrderCustomerservice/GetOrderDetails/{productId}/{merchantId}")
    suspend fun getOrderDetails(
        @Path("productId") productId : Int,
        @Path("merchantId") merchantId : String,
    ) : OrderDetailsResponse

    @POST("OrderCustomerservice/SearchProductsForCustomer")
    suspend fun getProducts(@Body productRequestModel: ProductsRequestModel) : List<ProductModel>

}