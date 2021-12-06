package com.youtube.tracker.application;

import com.youtube.tracker.service.TelegramBotService;
import com.youtube.tracker.service.YouTubeTrackerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class YouTubeTrackerApplication {
    @Value("#{'${youtube.api-channel-ids}'.split(',')}")
    private List<String> channels;

    private final TelegramBotService telegramBotService;
    private final YouTubeTrackerService youTubeTrackerService;

    public void trackChannels(){
        //todo
    }
}
