package com.youtube.tracker.service
import com.youtube.tracker.feign.TelegramFeign
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class TelegramService(
    @Value("\${telegram-api.channel-id}")
    private val defaultTargetChannel: String,
    private val feign: TelegramFeign
) {
    fun sendMessage(message: String, targetChannel: String?) {
        val finalTargetChannel = targetChannel ?: defaultTargetChannel
        feign.sendMessageToChannel(finalTargetChannel, message)
    }

    companion object {
        private const val CHAT_NOT_FOUND = "Bad Request: chat not found"
    }
}