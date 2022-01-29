package com.youtube.tracker.controller

import com.youtube.tracker.application.YoutubeTrackerApplication
import com.youtube.tracker.models.ResponseWrapper
import com.youtube.tracker.models.request.TrackerRequest
import com.youtube.tracker.util.loggerFor
import com.youtube.tracker.validations.Validator
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
class TrackerController(
    private val application: YoutubeTrackerApplication,
    private val validator: Validator
) {

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    fun entryPoint() = homeMessage

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
        private const val homeMessage = "HELLO WELCOME TO YOUTUBE TRACKER"
    }
}
