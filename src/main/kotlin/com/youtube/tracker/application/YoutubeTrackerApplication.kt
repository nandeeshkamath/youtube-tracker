package com.youtube.tracker.application

import com.youtube.tracker.models.request.TrackerRequest
import com.youtube.tracker.models.response.Item
import com.youtube.tracker.models.response.YoutubeSearchResponse
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
        val keywords = request.concatenateKeywords()
        val publishedAfter = request.publishedAfter()

        request.channels.forEach { channel ->
            val searchResults = youtubeService.search(
                channel = channel,
                keywords = keywords,
                publishedAfter = publishedAfter,
                resultType = request.resultType
            )
            searchResults
                .filterByKeyword(request.keywords)
                .generateLinks()
                .send(request.targetChannel)
        }
    }

    internal fun YoutubeSearchResponse?.filterByKeyword(keywords: Set<String>): List<Item> =
        this?.items?.filter { item ->
            val videoTitle = item.snippet?.title?.lowercase()
            keywords.forEach { keyword ->
                if (videoTitle?.contains(keyword) == true) {
                    return@filter true
                }
            }
            return@filter false
        } ?: emptyList()

    internal fun List<Item>.generateLinks() =
        this.mapNotNull { item ->
            item.id?.videoId?.toYoutubeLink()
        }

    internal fun List<String>.send(targetChannel: String?) =
        this.forEach { link ->
            log.info("Sending message to telegram with body : $link")
            telegramService.sendMessage(
                link,
                targetChannel
            )
        }

    internal fun TrackerRequest.concatenateKeywords(separator: String = " ") =
        this.keywords.joinToString { separator }

    internal fun TrackerRequest.publishedAfter() =
        INSTANT_ISO_FORMATTER.format(
            Instant.now().minus(this.interval ?: defaultTimeInterval, ChronoUnit.HOURS)
        )

    internal fun String.toYoutubeLink() = "https://youtu.be/$this"

    companion object {
        private val log = loggerFor(YoutubeTrackerApplication::class)
    }
}
