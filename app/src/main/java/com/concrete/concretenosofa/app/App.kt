package com.concrete.concretenosofa.app

import android.app.Application
import com.concrete.concretenosofa.di.koinInjector

class App : Application(){
    override fun onCreate() {
        koinInjector()
        super.onCreate()
    }
}