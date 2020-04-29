package com.concrete.concretenosofa.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.concrete.concretenosofa.R
import com.concrete.concretenosofa.extensions.getBackgroundColor
import com.concrete.concretenosofa.extensions.getLogStringDate
import com.concrete.concretenosofa.extensions.getSalutation
import com.concrete.concretenosofa.models.*
import com.concrete.concretenosofa.repository.ServicesRepository
import com.concrete.concretenosofa.utils.*
import java.util.*

class HomeViewModel(private val services: ServicesRepository) : ViewModel(){

    val weatherInfo = MutableLiveData<ViewState>()
    val calendar = Calendar.getInstance()

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

    fun getIconWeather(iconCode: String) =
        when(iconCode){
            CLEAR_SKY_DAY_01D -> R.drawable.ic_01d
            CLEAR_SKY_NIGHT_01N ->R.drawable.ic_01n
            FEW_CLOUDS_DAY_02D ->R.drawable.ic_02d
            FEW_CLOUDS_NIGHT_02N ->R.drawable.ic_02n
            SCATTERED_CLOUDS_DAY_3D ->R.drawable.ic_03d
            SCATTERED_CLOUDS_NIGHT_3N ->R.drawable.ic_03n
            BROKEN_CLOUDS_DAY_4D ->R.drawable.ic_03d
            BROKEN_CLOUDS_NIGHT_4N ->R.drawable.ic_03n
            SHOWER_RAIN_DAY_9D -> R.drawable.ic_09d
            SHOWER_RAIN_NIGHT_9N ->R.drawable.ic_09n
            RAIN_DAY_10D ->R.drawable.ic_10d
            RAIN_NIGHT_10N ->R.drawable.ic_10n
            THUNDERSTORM_DAY_11D ->R.drawable.ic_11d
            THUNDERSTORM_NIGHT_11N ->R.drawable.ic_11n
            else -> R.drawable.ic_01d
        }

    fun getWelcomeInfo() =
        WelcomeInfo(calendar.getSalutation(),
            calendar.getLogStringDate(),
            calendar.getBackgroundColor())

}