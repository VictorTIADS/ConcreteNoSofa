package com.concrete.concretenosofa.home

import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.concretesolutions.kappuccino.utils.doWait
import com.concrete.concretenosofa.R
import com.concrete.concretenosofa.home.HomeActivityArrange.Companion.WITH_ERROR_REQUEST
import com.concrete.concretenosofa.models.WelcomeInfo
import com.concrete.concretenosofa.server.MockWebServerRule
import com.concrete.concretenosofa.utils.*
import io.mockk.every
import io.mockk.mockkClass
import io.mockk.mockkConstructor
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeActivityTest {

    @get:Rule
    val mockWebServer = MockWebServerRule()

    @Test
    fun givenScreenIsLoading_whenOpenHomeActivity_shouldShowLoading() {
        arrange {
            launchActivity()
        }
        assert {
            isLoadingDisplayed()
        }
    }

    @Test
    fun givenScreenError_whenOpenHomeActivity_shouldShowErrorView() {
        arrange {
            mockRequest(WITH_ERROR_REQUEST)
            launchActivity()
        }
        assert {
            isErrorDisplayed()
        }
    }

    @Test
    fun givenScreenHasLoaded_whenOpenHomeActivity_shouldShowWelcomeInfo() {
        arrange {
            mockRequest()
            launchActivity()
        }
        assert {
            doWait(5000)
            isWelcomeInfoDisplayed()
        }
    }

    @Test
    fun givenClearSkyDay_whenOpenHomeActivity_shouldShowCorrectInformation() {
        arrange {
            mockRequest(weatherState = CLEAR_SKY_DAY_01D)
            launchActivity()
        }
        assert {
            isWeatherStateClearSkyDay()

        }
    }

    @Test
    fun givenClearSkyNight_whenOpenHomeActivity_shouldShowCorrectInformation() {
        arrange {
            mockRequest(weatherState = CLEAR_SKY_NIGHT_01N)
            launchActivity()
        }
        assert {
            isWeatherStateClearSkyNight()

        }
    }

    @Test
    fun givenFewCloudsDay_whenOpenHomeActivity_shouldShowCorrectInformation() {
        arrange {
            mockRequest(weatherState = FEW_CLOUDS_DAY_02D)
            launchActivity()
        }
        assert {
            isWeatherIconCorrect(FEW_CLOUDS_DAY_02D)

        }
    }

    @Test
    fun givenFewCloudsNight_whenOpenHomeActivity_shouldShowCorrectInformation() {
        arrange {
            mockRequest(weatherState = FEW_CLOUDS_NIGHT_02N)
            launchActivity()
        }
        assert {
            isWeatherIconCorrect(FEW_CLOUDS_NIGHT_02N)

        }
    }

    @Test
    fun givenScatteredCloudsDay_whenOpenHomeActivity_shouldShowCorrectInformation() {
        arrange {
            mockRequest(weatherState = SCATTERED_CLOUDS_DAY_3D)
            launchActivity()
        }
        assert {
            isWeatherIconCorrect(SCATTERED_CLOUDS_DAY_3D)

        }
    }

    @Test
    fun givenScatteredCloudsNight_whenOpenHomeActivity_shouldShowCorrectInformation() {
        arrange {
            mockRequest(weatherState = SCATTERED_CLOUDS_NIGHT_3N)
            launchActivity()
        }
        assert {
            isWeatherIconCorrect(SCATTERED_CLOUDS_NIGHT_3N)

        }
    }

    @Test
    fun givenBrokenCloudsDay_whenOpenHomeActivity_shouldShowCorrectInformation() {
        arrange {
            mockRequest(weatherState = BROKEN_CLOUDS_DAY_4D)
            launchActivity()
        }
        assert {
            isWeatherIconCorrect(BROKEN_CLOUDS_DAY_4D)

        }
    }

    @Test
    fun givenBrokenCloudsNight_whenOpenHomeActivity_shouldShowCorrectInformation() {
        arrange {
            mockRequest(weatherState = BROKEN_CLOUDS_NIGHT_4N)
            launchActivity()
        }
        assert {
            isWeatherIconCorrect(BROKEN_CLOUDS_NIGHT_4N)

        }
    }

    @Test
    fun givenShowerRainDay_whenOpenHomeActivity_shouldShowCorrectInformation() {
        arrange {
            mockRequest(weatherState = SHOWER_RAIN_DAY_9D)
            launchActivity()
        }
        assert {
            isWeatherIconCorrect(SHOWER_RAIN_DAY_9D)

        }
    }

    @Test
    fun givenShowerRainNight_whenOpenHomeActivity_shouldShowCorrectInformation() {
        arrange {
            mockRequest(weatherState = SHOWER_RAIN_NIGHT_9N)
            launchActivity()
        }
        assert {
            isWeatherIconCorrect(SHOWER_RAIN_NIGHT_9N)

        }
    }

    @Test
    fun givenRainDay_whenOpenHomeActivity_shouldShowCorrectInformation() {
        arrange {
            mockRequest(weatherState = RAIN_DAY_10D)
            launchActivity()
        }
        assert {
            isWeatherIconCorrect(RAIN_DAY_10D)

        }
    }

    @Test
    fun givenRainNight_whenOpenHomeActivity_shouldShowCorrectInformation() {
        arrange {
            mockRequest(weatherState = RAIN_NIGHT_10N)
            launchActivity()
        }
        assert {
            isWeatherIconCorrect(RAIN_NIGHT_10N)

        }
    }

    @Test
    fun givenThunderstormDay_whenOpenHomeActivity_shouldShowCorrectInformation() {
        arrange {
            mockRequest(weatherState = THUNDERSTORM_DAY_11D)
            launchActivity()
        }
        assert {
            isWeatherIconCorrect(THUNDERSTORM_DAY_11D)

        }
    }

    @Test
    fun givenThunderstormNight_whenOpenHomeActivity_shouldShowCorrectInformation() {
        arrange {
            mockRequest(weatherState = THUNDERSTORM_NIGHT_11N)
            launchActivity()
        }
        assert {
            isWeatherIconCorrect(THUNDERSTORM_NIGHT_11N)

        }
    }

    @Test
    fun givenLongTimeRequest_whenFetchData_shouldShowError() {

    }

    @Test
    fun givenErrorScreen_whenClickOnTryAgain_shouldShowWeatherInfo() {

    }

}