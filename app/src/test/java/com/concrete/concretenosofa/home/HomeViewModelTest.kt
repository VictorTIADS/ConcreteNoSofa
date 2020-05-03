package com.concrete.concretenosofa.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.concrete.concretenosofa.di.network
import com.concrete.concretenosofa.di.utils
import com.concrete.concretenosofa.di.viewModels
import com.concrete.concretenosofa.models.Error
import com.concrete.concretenosofa.models.Loading
import com.concrete.concretenosofa.models.ViewState
import com.concrete.concretenosofa.network.RetrofitConfig
import com.concrete.concretenosofa.repository.ServicesRepository
import com.concrete.concretenosofa.viewmodel.HomeViewModel
import io.mockk.*
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.definition.Kind
import org.koin.core.inject
import org.koin.dsl.koinApplication
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.check.checkModules
import org.koin.test.mock.MockProviderRule
import org.mockito.Mockito
import java.util.*


@RunWith(JUnit4::class)
class HomeViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var retrofitConfig: RetrofitConfig

    lateinit var services: ServicesRepository

    lateinit var homeViewModel: HomeViewModel

    val observer = Observer<ViewState>{}

    @Before
    fun setup() {
        retrofitConfig = RetrofitConfig()
        services = ServicesRepository(retrofitConfig)
        homeViewModel = HomeViewModel(services)
        homeViewModel.weatherInfoObservable.observeForever(observer)
    }

    @Test
    fun givenOnCreteViewModel_whenInit_shouldCallFetchWeatherInfo(){
        every { services.getWeather({Loading()},{Error()}) } throws  Throwable("Api error")
        homeViewModel.fetchWeatherInfo()
        verify { observer.onChanged(Loading()) }
        verify { observer.onChanged(Error()) }
    }

}