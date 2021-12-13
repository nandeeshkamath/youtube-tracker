package com.youtube.tracker.service;

import com.youtube.tracker.feign.YoutubeTrackerFeign;
import com.youtube.tracker.models.YoutubeSearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Service
@RequiredArgsConstructor
public class YouTubeTrackerService {
    private final YoutubeTrackerFeign feign;
    @Value("${youtube.api-key}")
    private String apiKey;

    @Value("#{'${youtube.api-channel-ids}'.split(',')}")
    private List<String> channels;

    public YoutubeSearchResponse search(
            @NotBlank String part,
            @NotBlank String channel,
            @NotBlank String keyword,
            @NotBlank String publishedAfter
    ) {
        return feign.search(
                part,
                channel,
                apiKey,
                keyword,
                publishedAfter
        ).getBody();
    }
}