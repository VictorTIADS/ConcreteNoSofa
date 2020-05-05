package com.concrete.concretenosofa.rules

import androidx.test.core.app.ApplicationProvider
import com.concrete.concretenosofa.network.RetrofitConfig
import com.concrete.concretenosofa.repository.Services
import com.concrete.concretenosofa.repository.ServicesRepository
import com.concrete.concretenosofa.ui.WelcomeInfoServices
import com.concrete.concretenosofa.viewmodel.HomeViewModel
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
    single { ServicesRepository(get()) as Services }
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
       return object : Statement(){
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