package com.concrete.concretenosofa.features.weather

import androidx.lifecycle.*
import com.concrete.concretenosofa.features.weather.model.BaseModel
import com.concrete.concretenosofa.features.weather.model.WeatherRequestResponse
import com.concrete.concretenosofa.features.weather.usecase.Services
import kotlinx.coroutines.launch

class HomeViewModel(
    private val services: Services
) : ViewModel(), LifecycleObserver {

    private val weatherInfo = MutableLiveData<BaseModel<WeatherRequestResponse>>()
    val weatherInfoObservable: LiveData<BaseModel<WeatherRequestResponse>>
        get() = weatherInfo


    init {
        fetchWeatherInfo()
    }

    fun fetchWeatherInfo() {
        viewModelScope.launch {
            val response = services.getWeatherInfo()
            weatherInfo.postValue(response)
        }
    }

}