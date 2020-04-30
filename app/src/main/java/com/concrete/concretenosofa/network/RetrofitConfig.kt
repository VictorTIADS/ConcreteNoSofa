package com.concrete.concretenosofa.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitConfig {

    companion object{
        var BASE_URL = "https://api.openweathermap.org/data/2.5/"
    }

    val okHttpClient = OkHttpClient.Builder()
        .readTimeout(10L, TimeUnit.SECONDS)
        .connectTimeout(10L, TimeUnit.SECONDS)
        .callTimeout(10L,TimeUnit.SECONDS)
        .writeTimeout(10L,TimeUnit.SECONDS)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun retrofitRequest() = retrofit.create(Request::class.java)
}
