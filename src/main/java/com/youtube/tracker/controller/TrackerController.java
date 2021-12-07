package com.youtube.tracker.controller;

import com.youtube.tracker.application.YouTubeTrackerApplication;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TrackerController {
    private final YouTubeTrackerApplication youTubeTrackerApplication;

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public String entryPoint() {
        return "HELLO WELCOME TO YOUTUBE TRACKER";
    }

    @PostMapping("/track")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void track() {
        youTubeTrackerApplication.trackChannels();
    }
}