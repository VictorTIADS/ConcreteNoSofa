package com.concrete.concretenosofa.home

import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.concretesolutions.kappuccino.utils.doWait
import com.concrete.concretenosofa.home.HomeActivityArrange.Companion.TEN_SECONDS
import com.concrete.concretenosofa.home.HomeActivityArrange.Companion.WITH_DELAY
import com.concrete.concretenosofa.home.HomeActivityArrange.Companion.WITH_ERROR_REQUEST
import com.concrete.concretenosofa.server.MockWebServerRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeActivityTest {

    @get:Rule
    val mockWebServer = MockWebServerRule()

    @Test
    fun givenScreenIsLoading_whenOpenHomeActivity_shouldShowLoading(){
        arrange {
            launchActivity()
        }
        assert {
            isLoadingDisplayed()
        }
    }

    @Test
    fun givenScreenError_whenOpenHomeActivity_shouldShowErrorView(){
        arrange {
            mockRequest(WITH_ERROR_REQUEST)
            launchActivity()
        }
        assert {
            isErrorDisplayed()
        }
    }

    @Test
    fun givenScreenHasLoaded_whenOpenHomeActivity_shouldShowWelcomeInfo(){
        arrange {
            mockRequest()
            launchActivity()
        }
        assert {

        }
    }

}