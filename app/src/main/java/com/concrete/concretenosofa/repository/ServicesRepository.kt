package com.concrete.concretenosofa.repository

import com.concrete.concretenosofa.models.*
import com.concrete.concretenosofa.network.RetrofitConfig
import org.koin.core.KoinComponent

class ServicesRepository(private val retrofitConfig: RetrofitConfig) : KoinComponent, Services {

    override suspend fun getWeatherInfo(): BaseModel<WeatherRequestResponse> {

        lateinit var resultRequest: BaseModel<WeatherRequestResponse>

        val result = runCatching {

            val response = retrofitConfig.retrofitRequest().getWeather()

            resultRequest = if (response.isSuccessful) {

                response.body()?.let {
                    return BaseModel(BaseModel.Companion.STATUS.SUCCESS, it)
                } ?: run {
                    return BaseModel(BaseModel.Companion.STATUS.ERROR)
                }

            } else {
                return BaseModel(BaseModel.Companion.STATUS.ERROR)
            }
        }
        return if (result.isFailure) {
            BaseModel(BaseModel.Companion.STATUS.ERROR)
        } else {
            resultRequest
        }

    }
}