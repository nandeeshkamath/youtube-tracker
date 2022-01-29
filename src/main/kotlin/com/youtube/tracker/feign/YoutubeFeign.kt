package com.youtube.tracker.feign

import com.youtube.tracker.models.response.YoutubeSearchResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(value = "youtube-feign", url = "\${youtube-api.base-url}")
interface YoutubeFeign {
    @Suppress("LongParameterList")
    @GetMapping("/v3/search")
    fun search(
        @RequestParam("q") keyword: String,
        @RequestParam channelId: String,
        @RequestParam part: String,
        @RequestParam key: String,
        @RequestParam publishedAfter: String,
        @RequestParam("type") videoType: String,
        @RequestParam maxResults: Int,
        @RequestParam order: String,
        @RequestParam regionCode: String
    ): ResponseEntity<YoutubeSearchResponse>
}
