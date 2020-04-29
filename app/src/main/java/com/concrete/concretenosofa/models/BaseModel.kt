package com.concrete.concretenosofa.models

data class BaseModel<T>(var data: T?, val status: STATUS,val message:String?) {
    companion object {
        enum class STATUS {
            LOADING, SUCCESS, ERROR
        }
    }
}

sealed class ViewState

class Loading : ViewState()
data class Succes(val data: WeatherRequestResponse) : ViewState()
class Error : ViewState()