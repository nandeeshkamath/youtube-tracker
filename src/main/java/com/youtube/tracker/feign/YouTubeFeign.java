package com.youtube.tracker.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "youtube-feign", url = "{youtube-api.base-url}")
public interface YouTubeFeign {

    @GetMapping("/v3/channels")
    ResponseEntity getChannelList(@RequestParam("part") String part, @RequestParam String forUsername, @RequestParam String id, @RequestParam boolean managedByMe, @RequestParam boolean mine);
}
