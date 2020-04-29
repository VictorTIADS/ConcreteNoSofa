package com.concrete.concretenosofa.repository

import com.concrete.concretenosofa.models.WeatherRequestResponse
import com.concrete.concretenosofa.network.RetrofitConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ServicesRepository(private val retrofitConfig: RetrofitConfig) {

    fun getWeather(
        onRequestSucces: (data: WeatherRequestResponse) -> Unit,
        onRequestError: () -> Unit
    ) {
        retrofitConfig.retrofitRequest().getWeather()
            .enqueue(object : Callback<WeatherRequestResponse> {
                override fun onFailure(call: Call<WeatherRequestResponse>, t: Throwable) {
                    onRequestError()
                }
                override fun onResponse(
                    call: Call<WeatherRequestResponse>,
                    response: Response<WeatherRequestResponse>
                ) {
                    response.body()?.let {
                        onRequestSucces(it)
                    }
                }

            })
    }

}