package com.youtube.tracker.feign

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.http.ResponseEntity

// TODO: Add Configuration for server/client errors
@FeignClient(value = "telegram-bot-feign", url = "\${telegram-api.base-url}")
interface TelegramBotFeign {
    @GetMapping("/sendMessage")
    fun sendMessageToChannel(@RequestParam("chat_id") chatId: String, @RequestParam text: String): ResponseEntity<*>?
}