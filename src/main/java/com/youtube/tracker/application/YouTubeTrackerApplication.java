package com.youtube.tracker.application;

import com.youtube.tracker.service.TelegramBotService;
import com.youtube.tracker.service.YouTubeTrackerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class YouTubeTrackerApplication {
    private final TelegramBotService telegramBotService;
    private final YouTubeTrackerService youTubeTrackerService;
    @Value("#{'${youtube.api-channel-ids}'.split(',')}")
    private List<String> channels;

    @Async("asyncExecutor")
    public void trackChannels() {
        System.out.println("Execute method asynchronously. " + Thread.currentThread().getName());
    }

    public void sendMessage(String msg) {
        telegramBotService.sendMessage(msg);
    }
}