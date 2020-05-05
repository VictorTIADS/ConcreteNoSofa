package com.concrete.concretenosofa.rules

import com.concrete.concretenosofa.network.RetrofitConfig.Companion.BASE_URL
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import java.util.concurrent.TimeUnit

class MockWebServerRule : TestWatcher(){

    val mock : MockWebServer by lazy {
        MockWebServer()
    }

    override fun starting(description: Description?) {
        super.starting(description)
        mock.start()
        BASE_URL = mock.url("/data/2.5/").toString()
    }

    override fun finished(description: Description?) {
        super.finished(description)
        mock.shutdown()
    }
}