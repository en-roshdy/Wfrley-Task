package com.example.wafarlytask.models.create_order_model

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

data class CreateOrderResponse(
    val barcode: String = "",
    val id: Int =0,
    val priceAfterDiscountTotal: Double = 0.0,
    var errorString: String? = null,
    var throwable: Throwable? = null,
    var errorsResponse: ErrorResponse? = null
){

    fun getErrors(): ErrorResponse?{
        var errorResponse : ErrorResponse? = null
        if (errorString!= null){
            val gson = Gson()
            val type = object : TypeToken<ErrorResponse>() {}.type

            errorResponse =  gson.fromJson<ErrorResponse>(errorString, type)
        }
        return  errorResponse
    }
}