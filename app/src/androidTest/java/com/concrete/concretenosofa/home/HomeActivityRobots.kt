package com.concrete.concretenosofa.home

import androidx.test.core.app.ActivityScenario
import br.com.concretesolutions.kappuccino.assertions.VisibilityAssertions.displayed
import com.concrete.concretenosofa.R
import com.concrete.concretenosofa.models.WelcomeInfo
import com.concrete.concretenosofa.ui.HomeActivity
import com.concrete.concretenosofa.utils.*
import io.mockk.every
import io.mockk.mockkClass
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import java.util.*
import java.util.concurrent.TimeUnit


fun HomeActivityTest.arrange(func: HomeActivityArrange.() -> Unit) =
    HomeActivityArrange(mockWebServer.mock).apply { func() }

fun assert(func: HomeActivityAssert.() -> Unit) =
    HomeActivityAssert().apply { func() }

class HomeActivityArrange(val mockWebServer: MockWebServer) {

    companion object {
        const val WITH_SUCCESS_REQUEST = 200
        const val WITH_ERROR_REQUEST = 404
        const val WITH_DELAY = 10
        const val TEN_SECONDS = 10.toLong()
    }

    fun launchActivity() {
        val scenario =
            ActivityScenario.launch(HomeActivity::class.java)
    }

    fun mockRequest(
        code: Int = WITH_SUCCESS_REQUEST,
        delay: Long = 0,
        weatherState: String = CLEAR_SKY_DAY_01D
    ) {
        mockWebServer.enqueue(
            when (code) {
                WITH_SUCCESS_REQUEST -> {
                    MockResponse()
                        .setResponseCode(200)
                        .setHeadersDelay(delay, TimeUnit.SECONDS)
                        .setBody(MockedJsonReader.readmockedJson("$weatherState.json"))
                }
                else -> {
                    MockResponse()
                        .setHttp2ErrorCode(304)
                }
            }

        )
    }

}

class HomeActivityAssert {

    fun isLoadingDisplayed() {
        displayed {
            id(R.id.homeContainerLoading)
        }
    }

    fun isErrorDisplayed() {
        displayed {
            parent(R.id.homeContainerError) {
                id(R.id.homeButtonTryAgain)
                id(R.id.homeErrorIcon)
                id(R.id.homeErrorText)
            }
        }
    }

    fun isWelcomeInfoDisplayed() {
        displayed {
            id(R.id.homeTitleWelcome)
            id(R.id.homeSubTitleDate)
        }
    }

    fun isWeatherIconCorrect(iconCode: String) {
        when (iconCode){
            CLEAR_SKY_DAY_01D -> displayed { image(R.drawable.ic_01d) }
            CLEAR_SKY_NIGHT_01N -> displayed { image(R.drawable.ic_01n) }
            FEW_CLOUDS_DAY_02D -> displayed { image(R.drawable.ic_02d) }
            FEW_CLOUDS_NIGHT_02N -> displayed { image(R.drawable.ic_02n) }
            SCATTERED_CLOUDS_DAY_3D -> displayed { image(R.drawable.ic_03d) }
            SCATTERED_CLOUDS_NIGHT_3N -> displayed { image(R.drawable.ic_03n) }
            BROKEN_CLOUDS_DAY_4D -> displayed { image(R.drawable.ic_03d) }
            BROKEN_CLOUDS_NIGHT_4N -> displayed { image(R.drawable.ic_03n) }
            SHOWER_RAIN_DAY_9D -> displayed { image(R.drawable.ic_09d) }
            SHOWER_RAIN_NIGHT_9N -> displayed { image(R.drawable.ic_09n) }
            RAIN_DAY_10D -> displayed { image(R.drawable.ic_10d) }
            RAIN_NIGHT_10N -> displayed { image(R.drawable.ic_10n) }
            THUNDERSTORM_DAY_11D -> displayed { image(R.drawable.ic_11d) }
            THUNDERSTORM_NIGHT_11N -> displayed { image(R.drawable.ic_11n) }
        }
    }

    fun isWeatherStateClearSkyDay(){
        displayed {
            image(R.drawable.ic_01d)
            text("Recife")
            text("céu limpo")
            text("31")
            text("ºC")
        }
    }

    fun isWeatherStateClearSkyNight(){
        displayed {
            image(R.drawable.ic_01n)
            text("Recife")
            text("céu limpo")
            text("31")
            text("ºC")
        }
    }

    fun isWeatherStateFewCloudsDay(){
        displayed {
            image(R.drawable.ic_02d)
            text("Recife")
            text("Poucas nuvens")
            text("30")
            text("ºC")
        }
    }

    fun isWeatherStateFewCloudsNight(){
        displayed {
            image(R.drawable.ic_02n)
            text("Recife")
            text("Poucas nuvens")
            text("30")
            text("ºC")
        }
    }



}