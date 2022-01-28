package com.youtube.tracker.controller

import com.youtube.tracker.application.YoutubeTrackerApplication
import com.youtube.tracker.models.ResponseWrapper
import com.youtube.tracker.models.request.TrackerRequest
import com.youtube.tracker.util.loggerFor
import com.youtube.tracker.validations.Validator
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
class TrackerController(
    private val application: YoutubeTrackerApplication,
    private val validator: Validator
) {

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    fun entryPoint() = "HELLO WELCOME TO YOUTUBE TRACKER"

    @PostMapping("/track")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun track(@RequestBody request: @Valid TrackerRequest): ResponseWrapper<Nothing?> {
        log.info("Received request to track: {}", request)

        validator
            .validateTargetChannel(request.targetChannel)

        application.track(request)
        return ResponseWrapper.success()
    }

    companion object {
        private val log = loggerFor(TrackerController::class)
    }
}