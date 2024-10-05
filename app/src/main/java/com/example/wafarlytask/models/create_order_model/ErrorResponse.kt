package com.example.wafarlytask.models.create_order_model

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("") val errors : List<String>
)