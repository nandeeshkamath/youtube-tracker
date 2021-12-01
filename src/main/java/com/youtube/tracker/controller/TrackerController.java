package com.youtube.tracker.controller;

import com.youtube.tracker.feign.YoutubeTrackerFeign;
import com.youtube.tracker.models.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TrackerController {
    @Autowired
    YoutubeTrackerFeign youtubeTrackerFeign;

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public String entryPoint() {
        return "HELLO WELCOME TO YOUTUBE TRACKER";
    }

    @PostMapping("/track")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseWrapper track() {
        return ResponseWrapper.success();
    }
}