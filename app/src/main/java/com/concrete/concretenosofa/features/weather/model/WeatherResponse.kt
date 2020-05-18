package com.concrete.concretenosofa.features.weather.model

data class WeatherResponse(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String)