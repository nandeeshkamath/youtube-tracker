package com.youtube.tracker.config

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.core.WireMockConfiguration
import com.github.tomakehurst.wiremock.stubbing.StubMapping
import org.awaitility.Awaitility
import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.context.event.ContextClosedEvent
import java.net.URL

class WireMockServerInitializer : ApplicationContextInitializer<ConfigurableApplicationContext> {

    override fun initialize(applicationContext: ConfigurableApplicationContext) {
        applicationContext.beanFactory.registerSingleton("wireMock", wiremockServer)
        TestPropertyValues.of("wiremock.server.port=${wiremockServer.port()}").applyTo(applicationContext)
    }

    companion object {
        private val wiremockServer: WireMockServer by lazy {
            val wiremockServer = WireMockServer(WireMockConfiguration().dynamicPort())
            WireMock.configureFor(WireMock(wiremockServer))
            wiremockServer.start()
            HealthApi.stubHealth()
            Awaitility.await()
                .until {
                    val start = System.currentTimeMillis()
                    URL("http://127.0.0.1:${wiremockServer.port()}/actuator/health").readText()
                    val duration = System.currentTimeMillis() - start

                    return@until duration < 100
                }
            return@lazy wiremockServer
        }
    }
}

object HealthApi {
    fun stubHealth(): StubMapping = WireMock.stubFor(
        WireMock.get(WireMock.urlPathEqualTo("/actuator/health"))
            .willReturn(WireMock.okJson("""{"status": "OK"}"""))
    )
}