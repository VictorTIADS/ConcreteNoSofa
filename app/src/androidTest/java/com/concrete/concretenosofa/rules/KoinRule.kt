package com.concrete.concretenosofa.rules

import androidx.test.core.app.ApplicationProvider
import com.concrete.concretenosofa.app.network.RetrofitConfig
import com.concrete.concretenosofa.features.weather.usecase.Services
import com.concrete.concretenosofa.features.weather.usecase.GetWeather
import com.concrete.concretenosofa.features.weather.utils.WelcomeInfoServices
import com.concrete.concretenosofa.features.weather.HomeViewModel
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import java.util.*

val testKoinViewmodelModule = module {
    viewModel { HomeViewModel(get()) }
}

val testKoinNetworkModule = module {
    single { RetrofitConfig() }
    single {
        GetWeather(
            get()
        ) as Services
    }
}

val testKoinUtilsModule = module {
    factory { Calendar.getInstance() }
    factory {
        WelcomeInfoServices(
            get(),
            get()
        )
    }
}

class KoinRule() : TestRule {
    override fun apply(base: Statement?, description: Description?): Statement {
        return object : Statement() {
            override fun evaluate() {
                try {
                    startKoin {
                        androidContext(ApplicationProvider.getApplicationContext())
                        modules(
                            listOf(
                                testKoinViewmodelModule,
                                testKoinNetworkModule,
                                testKoinUtilsModule
                            )
                        )
                    }
                    base?.evaluate()
                } finally {
                    stopKoin()
                }
            }

        }
    }

}