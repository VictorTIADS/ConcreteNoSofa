package com.concrete.concretenosofa

import com.concrete.concretenosofa.models.*
import retrofit2.Response

private val coord = CoordResponse(1.0, 1.0)
private val weather = WeatherResponse(1, "main", "description", "03d")
private val listWeather = listOf<WeatherResponse>(weather)
private val mainResponse = MainResponse(30.0, 30.0, 30.0, 30.0, 30.0, 30.0)
private val wind = WindResponse(40.0, 40.0)
private val clouds = CloundsResponse(30.0)
private val sys = SysResponse(1, 12, "BR", 400.0, 400.0)

val weatherMock = WeatherRequestResponse(
    coord,
    listWeather,
    "",
    mainResponse,
    30.0,
    wind,
    clouds,
    40.0,
    sys,
    30.0,
    40.0,
    "Recife",
    30
)

fun mockBaseModelSuccessWeatherInfo() = BaseModel(BaseModel.Companion.STATUS.SUCCESS,
    weatherMock
)

fun mockWeatherSuccessResponse() = Response.success(weatherMock)