package com.concrete.concretenosofa.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.concrete.concretenosofa.R
import com.concrete.concretenosofa.extensions.*
import com.concrete.concretenosofa.models.Error
import com.concrete.concretenosofa.models.Loading
import com.concrete.concretenosofa.models.Succes
import com.concrete.concretenosofa.models.WeatherRequestResponse
import com.concrete.concretenosofa.utils.*
import com.concrete.concretenosofa.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.activity_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private val viewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setupWelcomeTexts()
        setObservable()
    }

    private fun setObservable() {
        viewModel.weatherInfo.observe(this, Observer {
            when(it){
                is Loading -> {
                    showLoading()
                }
                is Succes -> {
                    hideLoading()
                    setupWeatherInfo(it.data)
                }
                is Error -> {
                    hideLoading()
                    setupError()
                }
            }
        })
    }

    private fun setupError() {
        homecontainerError.fadeIn()
        homeButtonTryAgain.setOnClickListener {
            errorRetry()
        }
    }

    private fun errorRetry(){
        homecontainerError.fadeOut()
        showLoading()
        viewModel.fetchWeatherInfo()
    }

    private fun setupWeatherInfo(weatherInfo: WeatherRequestResponse) {
        homeWeatherTemp.text = weatherInfo.main.temp.toTempFormat()
        homeCityName.text = weatherInfo.name
        homeWeatherSubtitle.text = weatherInfo.weather.first().description
        setupIconTemp(weatherInfo.weather.first().icon)
        setupAllEnterAnimations()
    }

    private fun setupIconTemp(iconCode: String) {
        val imageResource = viewModel.getIconWeather(iconCode)
        homeIconWeather.setImageResource(imageResource)
    }

    private fun setupAllEnterAnimations() {
        homeIconWeather.showUp()
        homeContainerCity.slideIn(CITY_NAME_DURATION)
        homeWeatherSubtitle.slideIn(WEATHER_DESCRIPTION_DURATION)
        homeWeatherFormat.slideInRight(WEATHER_FORMAT_DURATION)
        homeWeatherTemp.slideIn(WEATHER_TEMP_DURATION)
        homeContainerWelcome.slideIn(CITY_NAME_DURATION)
    }

    private fun showLoading() {
        homeContainerLoading.fadeIn()
    }

    private fun hideLoading() {
        homeContainerLoading.fadeOut()
    }


    private fun setupWelcomeTexts() {
        val welcomeInfo = viewModel.getWelcomeInfo()
        homeTitleWelcome.text = welcomeInfo.salutation
        homeSubTitleDate.text = welcomeInfo.date
        setupHomeBackgroundColor(welcomeInfo.backgroundColor)
    }

    private fun setupHomeBackgroundColor(@ColorRes color: Int) {
        homeView.setBackgroundColor(ContextCompat.getColor(this,color))
    }
}
