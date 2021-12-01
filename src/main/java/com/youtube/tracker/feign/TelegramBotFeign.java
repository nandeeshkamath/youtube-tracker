package com.youtube.tracker.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

// TODO: Add Configuration for server/client errors
@FeignClient(value = "telegram-bot-feign", url = "{telegram-api.base-url}")
public interface TelegramBotFeign {

    @GetMapping("/sendMessage")
    ResponseEntity sendMessageToChannel(@RequestParam("chat_id") String chatId, @RequestParam String text);
}
