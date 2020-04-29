package com.concrete.concretenosofa.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.concrete.concretenosofa.models.Error
import com.concrete.concretenosofa.models.Loading
import com.concrete.concretenosofa.models.Succes
import com.concrete.concretenosofa.models.ViewState
import com.concrete.concretenosofa.repository.ServicesRepository

class HomeViewModel(private val services: ServicesRepository) : ViewModel(){

    val weatherInfo = MutableLiveData<ViewState>()

    init {
        fetchWeatherInfo()
    }

    fun fetchWeatherInfo(){
        weatherInfo.value = Loading()

        services.getWeather({
          weatherInfo.value = Succes(it)
        },{
            weatherInfo.value = Error()
        })
    }

}