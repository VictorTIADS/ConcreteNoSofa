package com.concrete.concretenosofa.features.weather.model

data class SysResponse(
    val type: Int,
    val id: Int,
    val country: String,
    val sunrise: Double,
    val sunset: Double)