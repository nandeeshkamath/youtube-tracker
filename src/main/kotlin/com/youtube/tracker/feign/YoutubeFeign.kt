package com.youtube.tracker.feign

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.http.ResponseEntity
import com.youtube.tracker.models.YoutubeSearchResponse

@FeignClient(value = "youtube-feign", url = "\${youtube-api.base-url}")
interface YoutubeFeign {
    @GetMapping("/v3/search")
    fun search(
        @RequestParam("q") keyword: String, @RequestParam channelId: String,
        @RequestParam part: String, @RequestParam key: String, @RequestParam publishedAfter: String,
        @RequestParam("type") videoType: String
    ): ResponseEntity<YoutubeSearchResponse>
}