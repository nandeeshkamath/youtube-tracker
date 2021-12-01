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
    private String targetChannelId;

    private final TelegramBotFeign feign;

    public void sendMessage(@NotNull String message) {
        feign.sendMessageToChannel(targetChannelId, message);
    }
}
