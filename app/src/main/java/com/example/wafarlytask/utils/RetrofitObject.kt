package com.example.wafarlytask.utils

import ResponseInterceptor
import android.util.Log
import com.example.wafarlytask.utils.Constants.BASE_URL
import com.google.gson.GsonBuilder
import com.ihsanbal.logging.LoggingInterceptor

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.internal.platform.Platform
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import java.util.logging.Level


object RetrofitObject {

    private const val TAG = "RetrofitInstance"
    private var retrofitObject: Retrofit? = null


    var getNetworkInstance : Retrofit? = init()

    private fun init(): Retrofit {
        Log.d(TAG, "init: ")
        if (retrofitObject == null) {
            Log.d(TAG, "init: New Init")
            val loggingInterceptor: LoggingInterceptor = LoggingInterceptor.Builder()
//                .loggable(true)
                .setLevel(com.ihsanbal.logging.Level.BASIC)
                .log(Platform.INFO)
                .request("request")
                .response("response")
                .addHeader("Accept", "application/json")
                .addHeader("charset", "utf-8")
                .addHeader("Content-Type", "application/json")
//                .addHeader("Authorization", "Bearer $token")
//                .addHeader("Accept-Language", Lingver.getInstance().getLocale().language)
//                .addHeader("version", BuildConfig.VERSION_NAME)
                .build()

            val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(1000, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .addInterceptor(ResponseInterceptor())
                .readTimeout(1000, TimeUnit.SECONDS)
                .writeTimeout(1000, TimeUnit.SECONDS)
//                .followRedirects(false)
//                .followSslRedirects(false)
                .build()
            val gson = GsonBuilder()
                .setLenient()
                .create()
            retrofitObject = Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient).build()

        }
        return retrofitObject!!
    }

    class gfgInterceptor : Interceptor {




        override fun intercept(chain: Interceptor.Chain): Response {
            val aRequest: Request = chain.request()
            val aResponse = chain.proceed(aRequest)
            Log.d(TAG, "intercept: Error ${aResponse.code}")
            when (aResponse.code) {
                400 -> {
                    // Show Bad Request Error Message
                    Log.d(TAG, "intercept: Error $400")
                }
                401 -> {
                    // Show UnauthorizedError Message

                }

                403 -> {
                    // Show Forbidden Message
                }

                404 -> {
                    // Show NotFound Message
                }

                // ... and so on
            }
            return aResponse
        }
    }



    fun errorToString(e: Throwable): String {
        return (e as? HttpException)?.response()?.errorBody()?.string()!!

    }

    fun updateToken() {
        getNetworkInstance = null
        retrofitObject = null
        Log.d(TAG, "updateToken: Retrofit client ${retrofitObject == null}")
        getNetworkInstance = init()
    }
}

