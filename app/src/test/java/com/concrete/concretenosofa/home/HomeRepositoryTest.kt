package com.concrete.concretenosofa.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.concrete.concretenosofa.mockBaseModelSuccessWeatherInfo
import com.concrete.concretenosofa.mockWeatherSuccessResponse
import com.concrete.concretenosofa.network.RetrofitConfig
import com.concrete.concretenosofa.repository.ServicesRepository
import com.concrete.concretenosofa.rules.CoroutineTestRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class HomeRepositoryTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    val api = mockk<RetrofitConfig>()
    val service = ServicesRepository(api)

    @Test
    fun givenRepositorySuccessCall_whenCallGetWeatherInfo_shouldReturnBaseModelObjectCreated() = runBlocking {
        coEvery { api.retrofitRequest().getWeather() } returns mockWeatherSuccessResponse()
        assertEquals(mockBaseModelSuccessWeatherInfo(), service.getWeatherInfo())
    }

}