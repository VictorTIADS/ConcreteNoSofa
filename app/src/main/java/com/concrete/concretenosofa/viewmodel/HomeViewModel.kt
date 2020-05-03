package com.concrete.concretenosofa.viewmodel

import androidx.lifecycle.*
import com.concrete.concretenosofa.R
import com.concrete.concretenosofa.models.*
import com.concrete.concretenosofa.repository.ServicesRepository
import com.concrete.concretenosofa.utils.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(
    private val services: ServicesRepository
) : ViewModel(), LifecycleObserver {

    private val weatherInfo = MutableLiveData<ViewState>()
    val weatherInfoObservable: LiveData<ViewState>
        get() = weatherInfo


    init {
        fetchWeatherInfo()
    }

    fun fetchWeatherInfo() {
        weatherInfo.value = Loading()
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val response = services.getWeatherInfo()

                if (response.isSuccessful) {
                    response.body()?.let {
                        weatherInfo.postValue(Succes(it))
                    } ?: kotlin.run {
                        weatherInfo.postValue(Error())
                    }

                } else {
                    weatherInfo.postValue(Error())
                }

            }
        }
    }

}