package com.concrete.concretenosofa.features.weather.usecase

import com.concrete.concretenosofa.features.weather.model.BaseModel
import com.concrete.concretenosofa.features.weather.model.WeatherRequestResponse
import com.concrete.concretenosofa.app.network.RetrofitConfig
import org.koin.core.KoinComponent

class GetWeather(private val retrofitConfig: RetrofitConfig) : KoinComponent,
    Services {

    override suspend fun getWeatherInfo(): BaseModel<WeatherRequestResponse> {

        lateinit var resultRequest: BaseModel<WeatherRequestResponse>

        val result = runCatching {

            val response = retrofitConfig.retrofitRequest().getWeather()

            resultRequest = if (response.isSuccessful) {

                response.body()?.let {
                    return BaseModel(
                        BaseModel.Companion.STATUS.SUCCESS,
                        it
                    )
                } ?: run {
                    return BaseModel(
                        BaseModel.Companion.STATUS.ERROR
                    )
                }

            } else {
                return BaseModel(
                    BaseModel.Companion.STATUS.ERROR
                )
            }
        }
        return if (result.isFailure) {
            BaseModel(
                BaseModel.Companion.STATUS.ERROR
            )
        } else {
            resultRequest
        }

    }
}