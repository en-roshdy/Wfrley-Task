package com.example.wafarlytask.models


import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

data class BaseResponse<T>(
    val status: Boolean? = false,
    var throwable: Throwable? = null,
    var message: String = "",
    var messages: List<String> = ArrayList<String>(),
    var code: String? = null,
    var errorString: String? = null,
//    val links: Links? = null,
    var data: T? = null
){

}

