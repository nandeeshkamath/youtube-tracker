package com.youtube.tracker

import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.http.RequestMethod
import com.github.tomakehurst.wiremock.matching.MatchResult
import org.awaitility.Awaitility
import org.skyscreamer.jsonassert.JSONAssert
import org.skyscreamer.jsonassert.JSONCompareMode

object Util {

    fun compareJson(
        expected: String,
        actual: String,
        mode: JSONCompareMode = JSONCompareMode.STRICT
    ) = JSONAssert.assertEquals(expected, actual, mode)

    fun await(fn: () -> Unit) = Awaitility.await().untilAsserted(fn)

    fun assertYoutubeSearchCall(count: Int = 1) = assertCall("/v3/search", RequestMethod.GET, count)
    fun assertTelegramGetChannelInfo(count: Int = 1) = assertCall("/getChat", RequestMethod.GET, count)
    fun assertTelegramSendMessageCall(count: Int = 1) = assertCall("/sendMessage", RequestMethod.GET, count)

    fun assertCall(
        url: String,
        method: RequestMethod = RequestMethod.POST,
        count: Int = 1
    ) = await {
        WireMock.verify(WireMock.exactly(count), WireMock.requestMadeFor { request ->
            MatchResult.aggregate(
                WireMock.urlPathMatching(url).match(request.url),
                MatchResult.of(request.method == method)
            )
        })
    }
}