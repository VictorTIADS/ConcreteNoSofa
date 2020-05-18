package com.concrete.concretenosofa.home

import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.concretesolutions.kappuccino.utils.doWait
import com.concrete.concretenosofa.features.weather.utils.*
import com.concrete.concretenosofa.rules.AnimationRule
import com.concrete.concretenosofa.rules.KoinRule
import com.concrete.concretenosofa.rules.MockWebServerRule
import com.concrete.concretenosofa.testUtils.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

@RunWith(AndroidJUnit4::class)
class HomeActivityTest {

    @get:Rule
    val mockWebServer = MockWebServerRule()

    @get:Rule
    val animationRule = AnimationRule()

    @get:Rule
    val koinRule = KoinRule()

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
    fun givenScreenHasLoaded_whenIsMorning_shouldShowDayBackgroundColor() {
        arrange {
            mockWelcomeInfo(MORNING_TIME)
            mockRequest()
            launchActivity()
        }
        assert {
            isDayBrackgroundDisplayed()
        }
    }

    @Test
    fun givenScreenHasLoaded_whenIsNight_shouldShowNightBackgroundColor() {
        arrange {
            mockWelcomeInfo(NIGHT_TIME)
            mockRequest()
            launchActivity()
        }
        assert {
            isNightBrackgroundDisplayed()
        }
    }

    @Test
    fun givenScreenHasLoaded_whenIsMorning_shouldShowGoodMorning() {
        arrange {
            mockWelcomeInfo(MORNING_TIME)
            mockRequest()
            launchActivity()
        }
        assert {
            isGoodMorningDisplay()
        }
    }

    @Test
    fun givenScreenHasLoaded_whenIsEvening_shouldShowGoodMorning() {
        arrange {
            mockWelcomeInfo(EVENING_TIME)
            mockRequest()
            launchActivity()
        }
        assert {
            isGoodEveningDisplayed()
        }
    }

    @Test
    fun givenScreenHasLoaded_whenIsNight_shouldShowGoodNight() {
        arrange {
            mockWelcomeInfo(NIGHT_TIME)
            mockRequest()
            launchActivity()
        }
        assert {
            isGoodNightDisplayed()
        }
    }

    @Test
    fun givenScreenHasLoaded_whenIsApril_shouldShowCorrectDate() {
        arrange {
            mockWelcomeInfo(month = Calendar.APRIL)
            mockRequest()
            launchActivity()
        }
        assert {
            isLongDateDisplayedWithMonth("abril")
        }
    }

    @Test
    fun givenScreenHasLoaded_whenIsDecember_shouldShowCorrectDate() {
        arrange {
            mockWelcomeInfo(month = Calendar.DECEMBER)
            mockRequest()
            launchActivity()
        }
        assert {
            isLongDateDisplayedWithMonth("dezembro")
        }
    }

    @Test
    fun givenScreenHasLoaded_whenIsJuly_shouldShowCorrectDate() {
        arrange {
            mockWelcomeInfo(month = Calendar.JULY)
            mockRequest()
            launchActivity()
        }
        assert {
            isLongDateDisplayedWithMonth("julho")
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
            mockWelcomeInfo(NIGHT_TIME)
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
            isWeatherStateFewCloudsDay()

        }
    }

    @Test
    fun givenFewCloudsNight_whenOpenHomeActivity_shouldShowCorrectInformation() {
        arrange {
            mockWelcomeInfo(NIGHT_TIME)
            mockRequest(weatherState = FEW_CLOUDS_NIGHT_02N)
            launchActivity()
        }
        assert {
            isWeatherStateFewCloudsNight()
        }
    }

    @Test
    fun givenScatteredCloudsDay_whenOpenHomeActivity_shouldShowCorrectInformation() {
        arrange {
            mockRequest(weatherState = SCATTERED_CLOUDS_DAY_3D)
            launchActivity()
        }
        assert {
            isWeatherStateScatteredCloudsDay()

        }
    }

    @Test
    fun givenScatteredCloudsNight_whenOpenHomeActivity_shouldShowCorrectInformation() {
        arrange {
            mockWelcomeInfo(NIGHT_TIME)
            mockRequest(weatherState = SCATTERED_CLOUDS_NIGHT_3N)
            launchActivity()
        }
        assert {
            isWeatherStateScatteredCloudsNight()

        }
    }

    @Test
    fun givenBrokenCloudsDay_whenOpenHomeActivity_shouldShowCorrectInformation() {
        arrange {
            mockRequest(weatherState = BROKEN_CLOUDS_DAY_4D)
            launchActivity()
        }
        assert {
            isWeatherStateBrokenCloudsDay()
        }
    }

    @Test
    fun givenBrokenCloudsNight_whenOpenHomeActivity_shouldShowCorrectInformation() {
        arrange {
            mockWelcomeInfo(NIGHT_TIME)
            mockRequest(weatherState = BROKEN_CLOUDS_NIGHT_4N)
            launchActivity()
        }
        assert {
            isWeatherStateBrokenCloudsNight()

        }
    }

    @Test
    fun givenShowerRainDay_whenOpenHomeActivity_shouldShowCorrectInformation() {
        arrange {
            mockRequest(weatherState = SHOWER_RAIN_DAY_9D)
            launchActivity()
        }
        assert {
            isWeatherStateShowerRainDay()

        }
    }

    @Test
    fun givenShowerRainNight_whenOpenHomeActivity_shouldShowCorrectInformation() {
        arrange {
            mockWelcomeInfo(NIGHT_TIME)
            mockRequest(weatherState = SHOWER_RAIN_NIGHT_9N)
            launchActivity()
        }
        assert {
            isWeatherStateShowerRainNight()

        }
    }

    @Test
    fun givenRainDay_whenOpenHomeActivity_shouldShowCorrectInformation() {
        arrange {
            mockRequest(weatherState = RAIN_DAY_10D)
            launchActivity()
        }
        assert {
            isWeatherStateRainDay()

        }
    }

    @Test
    fun givenRainNight_whenOpenHomeActivity_shouldShowCorrectInformation() {
        arrange {
            mockWelcomeInfo(NIGHT_TIME)
            mockRequest(weatherState = RAIN_NIGHT_10N)
            launchActivity()
        }
        assert {
            isWeatherStateRainNight()

        }
    }

    @Test
    fun givenThunderstormDay_whenOpenHomeActivity_shouldShowCorrectInformation() {
        arrange {
            mockRequest(weatherState = THUNDERSTORM_DAY_11D)
            launchActivity()
        }
        assert {
            isWeatherStateThunderstormDay()
        }
    }

    @Test
    fun givenThunderstormNight_whenOpenHomeActivity_shouldShowCorrectInformation() {
        arrange {
            mockWelcomeInfo(NIGHT_TIME)
            mockRequest(weatherState = THUNDERSTORM_NIGHT_11N)
            launchActivity()
        }
        assert {
            isWeatherStateThunderstormNight()

        }
    }

    @Test
    fun givenErrorScreen_whenClickOnTryAgain_shouldShowWeatherInfo() {
        arrange {
            mockRequest(WITH_ERROR_REQUEST)
            launchActivity()
            mockRequest(WITH_SUCCESS_REQUEST)
        }
        act {
            clickOnTryAgain()
        }
        assert {
            isWeatherStateClearSkyDay()
        }
    }

    @Test
    fun givenScreenHasLoaded_whenFetchWeather_shouldShowModeDetailOption() {
        arrange {
            mockRequest()
            launchActivity()
        }
        assert {
            isMoreDetailOptionDisplayed()
        }
    }

    @Test
    fun givenScreenHasLoaded_whenClickOnMoreDetailOption_shouldShowBottomSheet() {
        arrange {
            mockRequest()
            launchActivity()
        }
        act {
            clickOnSeeModeDetail()
        }
        assert {
            isBottomSheetDisplayed()
        }
    }

    @Test
    fun givenScreenHasLoaded_whenBottomSheetIsOpen_shouldShowFeelsLike() {
        arrange {
            mockRequest()
            launchActivity()
        }
        act {
            clickOnSeeModeDetail()
        }
        assert {
            isFeelsLikeDisplayed()
        }
    }

    @Test
    fun givenScreenHasLoaded_whenBottomSheetIsOpen_shouldHumidity() {
        arrange {
            mockRequest()
            launchActivity()
        }
        act {
            clickOnSeeModeDetail()

        }
        assert {
            isHumidityDisplayed()
        }
    }

    @Test
    fun givenScreenHasLoaded_whenBottomSheetIsOpen_shouldShowWindSpeed() {
        arrange {
            mockRequest()
            launchActivity()
        }
        act {
            clickOnSeeModeDetail()
        }
        assert {
            isWindSpeedDisplayed()
        }
    }

    @Test
    fun givenScreenHasLoaded_whenBottomSheetIsOpen_shouldShowBottomSheetTitleWithTheCityName() {
        arrange {
            mockRequest()
            launchActivity()
        }
        act {
            clickOnSeeModeDetail()
        }
        assert {
            isBottomSheetWithCorrectCityNameDisplayed()
        }
    }


}