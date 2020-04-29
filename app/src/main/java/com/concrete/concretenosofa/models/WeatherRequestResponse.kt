package com.concrete.concretenosofa.models

data class WeatherRequestResponse(
    val coord: CoordResponse,
    val weather: List<WeatherResponse>,
    val base: String,
    val main: MainResponse,
    val visibility: Double,
    val wind: WindResponse,
    val clouds: CloundsResponse,
    val dt: Double,
    val sys: SysResponse,
    val timezone: Double,
    val id: Double,
    val name: String,
    val cod: Int)