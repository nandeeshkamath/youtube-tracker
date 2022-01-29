package com.youtube.tracker.validations

import com.youtube.tracker.feign.TelegramFeign
import com.youtube.tracker.util.loggerFor
import org.springframework.stereotype.Component

@Component
class Validator(private val telegramFeign: TelegramFeign) {

    fun validateTargetChannel(channel: String?) {
        if (channel == null) return
        kotlin.runCatching {
            telegramFeign.getChannelInfo(channel)
        }.onFailure { exception ->
            log.error("Failed to validate target channel: $channel", exception)
            throw exception
        }
    }

    companion object {
        private val log = loggerFor(Validator::class)
    }
}
