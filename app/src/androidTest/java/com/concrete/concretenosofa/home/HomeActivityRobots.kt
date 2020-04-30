package com.concrete.concretenosofa.home

import androidx.test.core.app.ActivityScenario
import br.com.concretesolutions.kappuccino.assertions.VisibilityAssertions.displayed
import com.concrete.concretenosofa.R
import com.concrete.concretenosofa.ui.HomeActivity
import com.concrete.concretenosofa.utils.MockedJsonReader
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.SocketPolicy
import java.time.Duration
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
        ActivityScenario.launch(HomeActivity::class.java)
    }

    fun mockRequest(code: Int = WITH_SUCCESS_REQUEST, delay: Long = 0) {
        mockWebServer.enqueue(
            when (code) {
                WITH_SUCCESS_REQUEST -> {
                    MockResponse()
                        .setResponseCode(200)
                        .setHeadersDelay(delay, TimeUnit.SECONDS)
                        .setBody(MockedJsonReader.readmockedJson("weather.json"))
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

}