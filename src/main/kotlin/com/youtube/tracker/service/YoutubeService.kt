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
        part: PartType, channel: String,
        keyword: String, publishedAfter: String,
        resultType: ResultType
    ) = feign.search(
        keyword, channel, part.value, apiKey, publishedAfter, resultType.value, 50
    ).body
}