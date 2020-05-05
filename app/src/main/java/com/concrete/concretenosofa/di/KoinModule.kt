package com.concrete.concretenosofa.di

import com.concrete.concretenosofa.network.RetrofitConfig
import com.concrete.concretenosofa.repository.Services
import com.concrete.concretenosofa.repository.ServicesRepository
import com.concrete.concretenosofa.ui.WelcomeInfoServices
import com.concrete.concretenosofa.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import java.util.*

val viewModels = module {
    viewModel { HomeViewModel(get()) }
}

val network = module {
    single { RetrofitConfig() }
    single { ServicesRepository(get()) as Services}
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