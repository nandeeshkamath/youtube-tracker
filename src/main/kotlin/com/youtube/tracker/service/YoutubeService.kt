package com.youtube.tracker.service

import com.youtube.tracker.enums.PartType
import com.youtube.tracker.enums.ResultType
import com.youtube.tracker.feign.YoutubeFeign
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class YoutubeService(
    @Value("\${youtube.api-key}")
    private val apiKey: String,
    private val feign: YoutubeFeign
) {
    fun search(
        part: PartType = PartType.SNIPPET,
        channel: String,
        keyword: String,
        publishedAfter: String,
        resultType: ResultType
    ) = feign.search(
        keyword = keyword,
        channelId = channel,
        part = part.value,
        key = apiKey,
        publishedAfter = publishedAfter,
        videoType = resultType.value,
        maxResults = MAX_RESULTS,
        order = DATE_ORDER,
        regionCode = INDIA_REGION_CODE
    ).body

    companion object {
        private const val MAX_RESULTS = 50
        private const val DATE_ORDER = "date"
        private const val INDIA_REGION_CODE = "IN"
    }
}
