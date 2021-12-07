package com.youtube.tracker.service;

import com.youtube.tracker.feign.YoutubeTrackerFeign;
import com.youtube.tracker.models.YoutubeSearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class YouTubeTrackerService {
    private final YoutubeTrackerFeign feign;
    @Value("${youtube.api-key}")
    private String apiKey;

    @Value("#{'${youtube.api-channel-ids}'.split(',')}")
    private List<String> channels;

    public ResponseEntity<YoutubeSearchResponse> search(@NotBlank String part, @NotBlank String channels, @NotBlank String keyword) {
        return feign.search(part, channels, apiKey, keyword, DateTimeFormatter.ISO_INSTANT.format(Instant.now()));
    }
}