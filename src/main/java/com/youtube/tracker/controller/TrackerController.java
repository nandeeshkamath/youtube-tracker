package com.youtube.tracker.controller;

import com.youtube.tracker.application.YouTubeTrackerApplication;
import com.youtube.tracker.models.ResponseWrapper;
import com.youtube.tracker.models.request.TrackerRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TrackerController {
    private final YouTubeTrackerApplication application;

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public String entryPoint() {
        return "HELLO WELCOME TO YOUTUBE TRACKER";
    }

    @PostMapping("/track")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseWrapper track(@RequestBody @Valid TrackerRequest request) {
        log.info("Received request to track: {}", request);
        application.trackChannels(request);
        return ResponseWrapper.success();
    }
}