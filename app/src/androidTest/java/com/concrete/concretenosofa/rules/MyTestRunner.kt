package com.concrete.concretenosofa.rules

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import com.concrete.concretenosofa.network.RetrofitConfig
import com.concrete.concretenosofa.repository.Services
import com.concrete.concretenosofa.repository.ServicesRepository
import com.concrete.concretenosofa.ui.WelcomeInfoServices
import com.concrete.concretenosofa.viewmodel.HomeViewModel
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import java.util.*

class MyTestRunner : AndroidJUnitRunner() {

    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(cl, myApplicationTest::class.java.name, context)
    }

}

val testKoinViewmodelModule = module {
    viewModel { HomeViewModel(get()) }
}

val testKoinNetworkModule = module {
    single { RetrofitConfig() }
    single { ServicesRepository(get()) as Services}
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

class myApplicationTest : Application()

class KoinRule() : TestRule {
    override fun apply(base: Statement?, description: Description?): Statement {
       return object : Statement(){
           override fun evaluate() {
               try {
                   startKoin {
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