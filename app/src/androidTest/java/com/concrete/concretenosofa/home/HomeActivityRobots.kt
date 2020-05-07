package com.concrete.concretenosofa.home

import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import br.com.concretesolutions.kappuccino.assertions.VisibilityAssertions.displayed
import br.com.concretesolutions.kappuccino.actions.ClickActions.click
import com.concrete.concretenosofa.R
import com.concrete.concretenosofa.testUtils.MORNING_TIME
import com.concrete.concretenosofa.testUtils.MockedJsonReader
import com.concrete.concretenosofa.testUtils.WITH_DELAY
import com.concrete.concretenosofa.testUtils.WITH_SUCCESS_REQUEST
import com.concrete.concretenosofa.ui.HomeActivity
import com.concrete.concretenosofa.ui.WelcomeInfoServices
import com.concrete.concretenosofa.utils.*
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.SocketPolicy
import org.koin.core.KoinComponent
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import java.util.*
import java.util.concurrent.TimeUnit


fun HomeActivityTest.arrange(func: HomeActivityArrange.() -> Unit) =
    HomeActivityArrange(mockWebServer.mock).apply { func() }

fun act(func: HomeActivityAct.() -> Unit) =
    HomeActivityAct().apply { func() }

fun assert(func: HomeActivityAssert.() -> Unit) =
    HomeActivityAssert().apply { func() }

class HomeActivityArrange(val mockWebServer: MockWebServer) : KoinComponent {

    fun launchActivity() {
        ActivityScenario.launch(HomeActivity::class.java)
    }

    fun mockWelcomeInfo(hour: Int = MORNING_TIME, month: Int = Calendar.JANUARY){
        val calendar = Calendar.getInstance()
        calendar.set(2020, month,1, hour,1)
        loadKoinModules(
            module {
                factory(override = true) { calendar }
                factory(override = true) {
                    WelcomeInfoServices(ApplicationProvider.getApplicationContext(),get()) }
            }
        )
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
                WITH_DELAY -> {
                    MockResponse()
                        .setHeadersDelay(delay, TimeUnit.SECONDS)
                        .setBodyDelay(delay, TimeUnit.SECONDS)
                        .setSocketPolicy(SocketPolicy.NO_RESPONSE)
                        .setBody("")
                }
                else -> {
                    MockResponse()
                        .setHttp2ErrorCode(304)
                }
            }

        )
    }
}

class HomeActivityAct {

    fun clickOnTryAgain() {
        click {
            id(R.id.homeButtonTryAgain)
        }
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

    fun isLongDateDisplayedWithMonth(month: String){
        displayed {
            allOf {
                id(R.id.homeSubTitleDate)
                text("1 de $month de 2020")
            }
        }
    }

    fun isGoodEveningDisplayed() {
        displayed {
            allOf {
                id(R.id.homeTitleWelcome)
                text("Boa Tarde")
            }
        }
    }

    fun isGoodNightDisplayed() {
        displayed {
            allOf {
                id(R.id.homeTitleWelcome)
                text("Boa Noite")
            }
        }
    }

    fun isGoodMorningDisplay(){
        displayed {
            allOf {
                id(R.id.homeTitleWelcome)
                text("Bom Dia")
            }
        }
    }

    fun isDayBrackgroundDisplayed(){
        displayed {
            background(R.color.colorBackgroundDay)
        }
    }

    fun isNightBrackgroundDisplayed(){
        displayed {
            background(R.color.colorBackgroundNight)
        }
    }

    fun isWeatherStateClearSkyDay() {
        displayed {
            image(R.drawable.ic_01d)
            text("Recife")
            text("céu limpo")
            text("31")
            text("ºC")
        }
    }

    fun isWeatherStateClearSkyNight() {
        displayed {
            image(R.drawable.ic_01n)
            text("Recife")
            text("céu limpo")
            text("31")
            text("ºC")
        }
    }

    fun isWeatherStateFewCloudsDay() {
        displayed {
            image(R.drawable.ic_02d)
            text("Recife")
            text("algumas nuvens")
            text("30")
            text("ºC")
        }
    }

    fun isWeatherStateFewCloudsNight() {
        displayed {
            image(R.drawable.ic_02n)
            text("Recife")
            text("algumas nuvens")
            text("30")
            text("ºC")
        }
    }

    fun isWeatherStateScatteredCloudsDay() {
        displayed {
            image(R.drawable.ic_03d)
            text("Recife")
            text("nuvens dispersas")
            text("29")
            text("ºC")
        }
    }

    fun isWeatherStateScatteredCloudsNight() {
        displayed {
            image(R.drawable.ic_03n)
            text("Recife")
            text("nuvens dispersas")
            text("29")
            text("ºC")
        }
    }

    fun isWeatherStateBrokenCloudsDay() {
        displayed {
            image(R.drawable.ic_03d)
            text("Recife")
            text("nuvens quebradas")
            text("28")
            text("ºC")
        }
    }

    fun isWeatherStateBrokenCloudsNight() {
        displayed {
            image(R.drawable.ic_03n)
            text("Recife")
            text("nuvens quebradas")
            text("28")
            text("ºC")
        }
    }

    fun isWeatherStateShowerRainDay() {
        displayed {
            image(R.drawable.ic_09d)
            text("Recife")
            text("chuva de banho")
            text("27")
            text("ºC")
        }
    }

    fun isWeatherStateShowerRainNight() {
        displayed {
            image(R.drawable.ic_09n)
            text("Recife")
            text("chuva de banho")
            text("27")
            text("ºC")
        }
    }

    fun isWeatherStateRainDay() {
        displayed {
            image(R.drawable.ic_10d)
            text("Recife")
            text("chuva")
            text("26")
            text("ºC")
        }
    }

    fun isWeatherStateRainNight() {
        displayed {
            image(R.drawable.ic_10n)
            text("Recife")
            text("chuva")
            text("26")
            text("ºC")
        }
    }

    fun isWeatherStateThunderstormDay() {
        displayed {
            image(R.drawable.ic_11d)
            text("Recife")
            text("trovoada")
            text("25")
            text("ºC")
        }
    }

    fun isWeatherStateThunderstormNight() {
        displayed {
            image(R.drawable.ic_11n)
            text("Recife")
            text("trovoada")
            text("25")
            text("ºC")
        }
    }


}