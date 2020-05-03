package com.concrete.concretenosofa.network

import com.concrete.concretenosofa.models.WeatherRequestResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Request {

    @GET("weather")
    suspend fun getWeather(
        @Query("q")search:String = "Recife",
        @Query("lang")language:String = "pt_br",
        @Query("units")units:String = "metric",
        @Query("appid")apiKey:String = "749127a0ec8d2e3758f400a844e8ed58"
    ): Response<WeatherRequestResponse>
}