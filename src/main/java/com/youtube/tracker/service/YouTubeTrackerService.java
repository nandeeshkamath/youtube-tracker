package com.youtube.tracker.service;

import com.youtube.tracker.feign.YoutubeTrackerFeign;
import com.youtube.tracker.models.YoutubeSearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;

@Service
@RequiredArgsConstructor
public class YouTubeTrackerService {
    private final YoutubeTrackerFeign feign;
    @Value("${youtube.api-key}")
    private String apiKey;

    public ResponseEntity<YoutubeSearchResponse> getChannels(@NotBlank String part, @NotBlank String channelId, @NotBlank String keyword) {
        return feign.getChannels(part, channelId, apiKey, keyword);
    }
}