package com.concrete.concretenosofa.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.concrete.concretenosofa.R
import com.concrete.concretenosofa.extensions.*
import com.concrete.concretenosofa.models.Error
import com.concrete.concretenosofa.models.Loading
import com.concrete.concretenosofa.models.Succes
import com.concrete.concretenosofa.models.WeatherRequestResponse
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
                    setupLoading()
                }
                is Succes -> {
                    setupWeatherInfo(it.data)
                }
                is Error -> {
                    setupError()
                }
            }
        })
    }

    private fun setupError() {

    }

    private fun setupWeatherInfo(weatherInfo: WeatherRequestResponse) {
        homeWeatherTemp.text = weatherInfo.main.temp.toTempFormat()
        homeCityName.text = weatherInfo.name
        homeWeatherSubtitle.text = weatherInfo.weather.first().description
        setupAllEnterAnimations()
    }

    private fun setupAllEnterAnimations() {
        homeIconWeather.showUp()
        homeContainerCity.slideIn(600)
        homeWeatherSubtitle.slideIn(700)
        homeWeatherFormat.slideInRight(900)
        homeWeatherTemp.slideIn(800)
    }

    private fun setupLoading() {

    }


    private fun setupWelcomeTexts() {
        homeTitleWelcome.text = "bom dia"
        homeSubTitleDate.text = "2 de janeiro de 2020"
    }
}
