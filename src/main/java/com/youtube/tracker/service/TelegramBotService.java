package com.youtube.tracker.service;

import com.youtube.tracker.feign.TelegramBotFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service
@RequiredArgsConstructor
public class TelegramBotService {
    @Value("${telegram-api.channel-id}")
    private String defaultTargetChannel;

    private final TelegramBotFeign feign;

    public void sendMessage(@NotNull String message, String targetChannel) {
        String finalTargetChannel = targetChannel != null ? targetChannel : defaultTargetChannel;
        feign.sendMessageToChannel(finalTargetChannel, message);
    }
}
