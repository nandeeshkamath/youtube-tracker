package com.youtube.tracker.application;

import com.youtube.tracker.models.request.TrackerRequest;
import com.youtube.tracker.models.YoutubeLink;
import com.youtube.tracker.models.YoutubeSearchResponse;
import com.youtube.tracker.service.TelegramBotService;
import com.youtube.tracker.service.YouTubeTrackerService;
import com.youtube.tracker.util.Formatters;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class YouTubeTrackerApplication {
    private final TelegramBotService telegramBotService;
    private final YouTubeTrackerService youTubeTrackerService;

    @Async("asyncExecutor")
    public void trackChannels(@NotNull TrackerRequest request) {
        String publishedAfter = Formatters.INSTANT_ISO_FORMATTER.format(
                Instant.now().minus(request.getInterval(), ChronoUnit.HOURS)
        );
        request.getChannels().forEach(channel -> {
            YoutubeSearchResponse response = youTubeTrackerService.search(
                    request.getType().getValue(),
                    channel,
                    request.getKeyword(),
                    publishedAfter
            );
            log.info("Response received from youtube: {}", response.getItems());
            response.getItems()
                    .stream()
                    .filter(item -> item.getSnippet().getTitle().toLowerCase().contains(request.getKeyword()))
                    .map(YoutubeSearchResponse.Item::getId)
                    .map(YoutubeSearchResponse.Id::getVideoId)
                    .filter(Objects::nonNull)
                    .map(YoutubeLink::new)
                    .forEach(
                            youtubeLink -> {
                                log.info("Sending message to telegram with body : {}", youtubeLink.get());
                                telegramBotService.sendMessage(
                                        youtubeLink.get(),
                                        request.getTargetChannel()
                                );
                            }
                    );
        });
    }
}