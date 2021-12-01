package com.youtube.tracker.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TrackerController {

    @GetMapping("/")
    public ResponseEntity<String> entryPoint() {
        return ResponseEntity.status(HttpStatus.OK).body("HELLO WELCOME TO YOUTUBE TRACKER");
    }
}