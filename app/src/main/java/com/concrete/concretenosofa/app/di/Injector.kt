package com.concrete.concretenosofa.app.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

fun Application.koinInjector(){
    startKoin {
        androidLogger()
        androidContext(this@koinInjector)
        modules(
            listOf(
                viewModels,
                network,
                utils
            )
        )
    }
}