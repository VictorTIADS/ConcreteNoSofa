package com.concrete.concretenosofa.viewmodel

import androidx.lifecycle.*
import com.concrete.concretenosofa.R
import com.concrete.concretenosofa.models.*
import com.concrete.concretenosofa.repository.Services
import com.concrete.concretenosofa.repository.ServicesRepository
import com.concrete.concretenosofa.utils.*
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
        weatherInfo.postValue(BaseModel(BaseModel.Companion.STATUS.LOADING))
        viewModelScope.launch {
            val response = services.getWeatherInfo()
            weatherInfo.postValue(response)
        }
    }

}