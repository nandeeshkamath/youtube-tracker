package com.youtube.tracker.feign;

import com.youtube.tracker.models.YoutubeSearchResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "youtube-feign", url = "${youtube-api.base-url}")
public interface YoutubeTrackerFeign {

    @GetMapping("/v3/search")
    ResponseEntity<YoutubeSearchResponse> getChannels(@RequestParam String part, @RequestParam String channelId, @RequestParam String key, @RequestParam String q);
}