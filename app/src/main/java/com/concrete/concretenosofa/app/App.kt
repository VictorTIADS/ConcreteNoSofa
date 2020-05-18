package com.concrete.concretenosofa.app

import android.app.Application
import com.concrete.concretenosofa.app.di.koinInjector

class App : Application(){
    override fun onCreate() {
        koinInjector()
        super.onCreate()
    }
}