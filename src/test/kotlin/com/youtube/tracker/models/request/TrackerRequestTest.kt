package com.youtube.tracker.models.request

import com.youtube.tracker.enums.ResultType
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class TrackerRequestTest {

    @Test
    fun `given request with only core keyword when fetched all keywords then set contains only core keyword`() {
        val keyword = "trailer"
        val request = stubRequest
            .copy(
                keyword = Keyword(
                    core = keyword
                )
            )
        Assertions.assertEquals(setOf(keyword), request.keyword.all)
    }

    @Test
    fun `given request with core and extra keywords when fetched all keywords then contains both core and extra`() {
        Assertions.assertEquals(setOf("trailer", "teaser"), stubRequest.keyword.all)
    }

    companion object {
        private val stubRequest = TrackerRequest(
            keyword = Keyword(
                core = "trailer",
                extras = setOf("teaser")
            ),
            interval = 2,
            resultType = ResultType.VIDEO,
            channels = setOf("channel1", "channel2"),
            targetChannel = "channel3"
        )
    }
}
