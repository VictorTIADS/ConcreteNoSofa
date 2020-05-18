package com.concrete.concretenosofa.features.weather.model

data class BaseModel<T>(val status: STATUS, var data: T? = null, val message: String? = null) {
    companion object {
        enum class STATUS {
            SUCCESS, ERROR
        }
    }
}

sealed class ViewState

class Loading : ViewState()
data class Succes(val data: WeatherRequestResponse) : ViewState()
class Error : ViewState()