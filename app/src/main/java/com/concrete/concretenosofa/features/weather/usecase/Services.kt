package com.concrete.concretenosofa.features.weather.usecase

import com.concrete.concretenosofa.features.weather.model.BaseModel
import com.concrete.concretenosofa.features.weather.model.WeatherRequestResponse

interface Services {

    suspend fun getWeatherInfo(): BaseModel<WeatherRequestResponse>

}