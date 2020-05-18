package com.concrete.concretenosofa.features.weather.model

data class MainResponse(
    val temp: Double,
    val feels_like: Double,
    val temp_min: Double,
    val temp_max: Double,
    val pressure: Double,
    val humidity: Double)