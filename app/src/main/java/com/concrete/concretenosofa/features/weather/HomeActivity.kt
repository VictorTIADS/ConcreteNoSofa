package com.concrete.concretenosofa.features.weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.concrete.concretenosofa.R
import com.concrete.concretenosofa.features.weather.components.showOptionsBottomSheet
import com.concrete.concretenosofa.features.weather.model.BaseModel
import com.concrete.concretenosofa.features.weather.model.WeatherRequestResponse
import com.concrete.concretenosofa.features.weather.model.WelcomeInfo
import com.concrete.concretenosofa.features.weather.utils.*
import kotlinx.android.synthetic.main.activity_home.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private val viewModel: HomeViewModel by viewModel()

    private val welcomeInfoServices: WelcomeInfoServices by inject()

    lateinit var weatherInfo: WeatherRequestResponse

    companion object {
        const val LOADING = 0
        const val ERROR = 1
        const val WEATHER = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setupWelcomeTexts(welcomeInfoServices.getWelcomeInfo())
        setObservable()
        setupBottomSheet()
    }

    private fun setupBottomSheet() {
        homeWeatherDetail.setOnClickListener {
            weatherInfo.let {
                this.showOptionsBottomSheet {
                    with(this) {
                        setTitleText(it.name)
                        setFeelsLikeValue(it.main.feels_like)
                        setHumidityValue(it.main.humidity)
                        setWindSpeedValue(it.wind.speed)
                    }
                }
            }
        }
    }

    private fun setObservable() {
        viewModel.weatherInfoObservable.observe(this, Observer {
            when (it.status) {
                BaseModel.Companion.STATUS.SUCCESS -> {
                    it.data?.let { data ->
                        weatherInfo = data
                        setupWeatherInfo(data)
                    } ?: run {
                        setupError()
                    }
                }
                BaseModel.Companion.STATUS.ERROR -> {
                    setupError()
                }
            }
        })
    }

    private fun displayLoading() {
        homeView.displayedChild = LOADING
        homeContainerLoading.fadeIn()
    }

    private fun displayError() {
        homeView.displayedChild = ERROR
        homeContainerError.fadeIn()
    }

    private fun displayWeather() {
        homeView.displayedChild = WEATHER
    }


    private fun setupError() {
        displayError()
        homeButtonTryAgain.setOnClickListener {
            errorRetry()
        }
    }

    private fun errorRetry() {
        homeContainerError.fadeOut()
        displayLoading()
        viewModel.fetchWeatherInfo()
    }

    private fun setupWeatherInfo(weatherInfo: WeatherRequestResponse) {
        displayWeather()
        homeWeatherTemp.text = weatherInfo.main.temp.toTempFormat()
        homeCityName.text = weatherInfo.name
        homeWeatherSubtitle.text = weatherInfo.weather.first().description
        setupIconTemp(weatherInfo.weather.first().icon)
        setupAllEnterAnimations()
    }

    private fun setupIconTemp(iconCode: String) {
        val imageResource = welcomeInfoServices.getIconWeather(iconCode)
        homeIconWeather.setImageResource(imageResource)
    }

    private fun setupAllEnterAnimations() {
        homeIconWeather.showUp()
        homeContainerCity.slideIn(CITY_NAME_DURATION)
        homeWeatherSubtitle.slideIn(WEATHER_DESCRIPTION_DURATION)
        homeWeatherFormat.slideInRight(WEATHER_FORMAT_DURATION)
        homeWeatherDetail.slideInRight(WEATHER_FORMAT_DURATION)
        homeWeatherTemp.slideIn(WEATHER_TEMP_DURATION)
        homeContainerWelcome.slideIn(CITY_NAME_DURATION)
    }

    private fun setupWelcomeTexts(welcomeInfo: WelcomeInfo) {
        homeTitleWelcome.text = welcomeInfo.salutation
        homeSubTitleDate.text = welcomeInfo.date
        setupHomeBackgroundColor(welcomeInfo.backgroundColor)
    }

    private fun setupHomeBackgroundColor(@ColorRes color: Int) {
        homeView.setBackgroundColor(ContextCompat.getColor(this, color))
    }
}
