package com.concrete.concretenosofa.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.concrete.concretenosofa.R
import com.concrete.concretenosofa.models.*
import com.concrete.concretenosofa.repository.ServicesRepository
import com.concrete.concretenosofa.utils.*

class HomeViewModel(
    private val services: ServicesRepository
) : ViewModel() {

    private val weatherInfo = MutableLiveData<ViewState>()
    val weatherInfoObservable: LiveData<ViewState>
        get() = weatherInfo


    init {
        fetchWeatherInfo()
    }

    fun fetchWeatherInfo() {
        weatherInfo.value = Loading()

        services.getWeather({
            weatherInfo.value = Succes(it)
        }, {
            weatherInfo.value = Error()
        })
    }

}