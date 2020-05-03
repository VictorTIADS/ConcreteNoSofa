package com.concrete.concretenosofa.repository

import androidx.lifecycle.MutableLiveData
import com.concrete.concretenosofa.models.*
import com.concrete.concretenosofa.network.RetrofitConfig
import com.concrete.concretenosofa.utils.weatherMock
import org.koin.core.KoinComponent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ServicesRepository(private val retrofitConfig: RetrofitConfig) : KoinComponent, Services {

    override suspend fun getWeatherInfo(): Response<WeatherRequestResponse> {
        return retrofitConfig.retrofitRequest().getWeather()
    }

}