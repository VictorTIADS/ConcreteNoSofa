package com.concrete.concretenosofa.di

import com.concrete.concretenosofa.network.RetrofitConfig
import com.concrete.concretenosofa.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModels = module {
    viewModel { HomeViewModel() }
}

val network = module {
    single { RetrofitConfig() }
}