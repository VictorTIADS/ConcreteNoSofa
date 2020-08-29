package com.concrete.concretenosofa.app.di

import com.concrete.concretenosofa.app.network.RetrofitConfig
import com.concrete.concretenosofa.features.weather.usecase.Services
import com.concrete.concretenosofa.features.weather.usecase.GetWeather
import com.concrete.concretenosofa.features.weather.utils.WelcomeInfoServices
import com.concrete.concretenosofa.features.weather.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import java.util.*

val viewModels = module {
    viewModel { HomeViewModel(get()) }
}

val network = module {
    single { RetrofitConfig() }
    single { GetWeather(get()) as Services }
}

val utils = module {
    factory { Calendar.getInstance() }
    factory {
        WelcomeInfoServices(
            get(),
            get()
        )
    }
}