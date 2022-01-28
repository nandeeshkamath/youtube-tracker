package com.youtube.tracker.feign

import com.youtube.tracker.models.response.ChannelInfo
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

// TODO: Add Configuration for server/client errors
@FeignClient(value = "telegram-feign", url = "\${telegram-api.base-url}")
interface TelegramFeign {
    @GetMapping("/sendMessage")
    fun sendMessageToChannel(@RequestParam("chat_id") chatId: String, @RequestParam text: String): ResponseEntity<*>?

    @GetMapping("/getChat")
    fun getChannelInfo(@RequestParam("chat_id") chatId: String): ResponseEntity<ChannelInfo>
}