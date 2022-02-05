package com.youtube.tracker.models.request

import com.youtube.tracker.enums.ResultType
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class TrackerRequestTest {

    @Test
    fun `given stub request when single keyword concatenated then no change`() {
        val keyword = "trailer"
        val keywordsConcatenated = stubRequest
            .copy(
                keywords = setOf(keyword)
            )
            .concatenateKeywords()
        Assertions.assertEquals(keyword, keywordsConcatenated)
    }

    @Test
    fun `given stub request when multiple keywords concatenated then keywords separated by space`() {
        val keywordsConcatenated = stubRequest.concatenateKeywords()
        Assertions.assertEquals("trailer teaser", keywordsConcatenated)
    }

    companion object {
        private val stubRequest = TrackerRequest(
            keywords = setOf("trailer", "teaser"),
            interval = 2,
            resultType = ResultType.VIDEO,
            channels = setOf("channel1", "channel2"),
            targetChannel = "channel3"
        )
    }
}
