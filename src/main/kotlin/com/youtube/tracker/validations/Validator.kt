package com.youtube.tracker.validations

import com.youtube.tracker.constants.ResultInfoConstants
import com.youtube.tracker.exception.ClientException
import com.youtube.tracker.feign.TelegramFeign
import org.springframework.stereotype.Component

@Component
class Validator(private val telegramFeign: TelegramFeign) {

    fun validateTargetChannel(channel: String?) {
        if (channel == null) return
        val response = telegramFeign.getChannelInfo(channel)
        if (!response.statusCode.is2xxSuccessful) {
            throw ClientException(ResultInfoConstants.BAD_REQUEST, response.body?.description)
        }
    }
}
