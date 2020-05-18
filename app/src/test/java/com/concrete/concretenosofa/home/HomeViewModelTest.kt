package com.concrete.concretenosofa.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.concrete.concretenosofa.mockBaseModelSuccessWeatherInfo
import com.concrete.concretenosofa.features.weather.usecase.GetWeather
import com.concrete.concretenosofa.rules.CoroutineTestRule
import com.concrete.concretenosofa.features.weather.HomeViewModel
import io.mockk.*
import junit.framework.Assert.assertEquals
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class HomeViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    val service = mockk<GetWeather>()

    lateinit var viewModel: HomeViewModel

    @Test
    fun givenOnCreteViewModel_whenInit_shouldCallFetchWeatherInfo(){
        coEvery { service.getWeatherInfo() } returns mockBaseModelSuccessWeatherInfo()
        viewModel =
            HomeViewModel(service)
        assertEquals(mockBaseModelSuccessWeatherInfo(), viewModel.weatherInfoObservable.value)
    }

}