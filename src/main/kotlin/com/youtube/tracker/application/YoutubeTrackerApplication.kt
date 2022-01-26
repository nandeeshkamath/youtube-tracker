package com.youtube.tracker.application

import com.youtube.tracker.models.YoutubeLink
import com.youtube.tracker.models.request.TrackerRequest
import com.youtube.tracker.service.TelegramService
import com.youtube.tracker.service.YoutubeService
import com.youtube.tracker.util.Formatters.INSTANT_ISO_FORMATTER
import com.youtube.tracker.util.loggerFor
import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import java.time.Instant
import java.time.temporal.ChronoUnit

@Service
class YoutubeTrackerApplication(
    @Value("\${application.default-time-interval}")
    private val defaultTimeInterval: Long,
    private val telegramService: TelegramService,
    private val youtubeService: YoutubeService
) {
    @Async("asyncExecutor")
    fun track(request: TrackerRequest) {
        val keywords = request.keywords.joinToString(",")
        val publishedAfter = INSTANT_ISO_FORMATTER.format(
            Instant.now().minus(request.interval ?: defaultTimeInterval, ChronoUnit.HOURS)
        )
        request.channels.forEach { channel ->
            val response = youtubeService.search(
                request.type,
                channel,
                keywords,
                publishedAfter,
                request.resultType
            )
            log.info("Response received from youtube: ${response?.items}")
            response?.items?.filter { item ->
                val videoTitle = item.snippet?.title?.lowercase()
                request.keywords.forEach { keyword ->
                    if (videoTitle?.contains(keyword) == true) {
                        return@filter true
                    }
                }
                return@filter false
            }?.mapNotNull { item ->
                item.id?.videoId?.let { videoId -> YoutubeLink(videoId) }?.get()
            }?.forEach { link ->
                log.info("Sending message to telegram with body : $link")
                telegramService.sendMessage(
                    link,
                    request.targetChannel
                )
            }
        }
    }

    companion object {
        private val log = loggerFor(YoutubeTrackerApplication::class)
    }
}