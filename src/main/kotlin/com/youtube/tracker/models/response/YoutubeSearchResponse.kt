package com.youtube.tracker.models.response

import java.time.OffsetDateTime

data class YoutubeSearchResponse(
    val kind: String? = null,
    val etag: String? = null,
    val nextPageToken: String? = null,
    val regionCode: String? = null,
    val pageInfo: PageInfo? = null,
    val items: List<Item>? = null
)

data class Item(
    val kind: String? = null,
    val etag: String? = null,
    val id: Id? = null,
    val snippet: Snippet? = null
)

data class Id(
    val kind: String? = null,
    val videoId: String? = null
)

data class Snippet(
    val publishedAt: OffsetDateTime? = null,
    val channelId: String? = null,
    val title: String? = null,
    val description: String? = null,
    val thumbnails: Thumbnails? = null,
    val channelTitle: String? = null,
    val liveBroadcastContent: String? = null,
    val publishTime: OffsetDateTime? = null
)

data class Thumbnails(
    val thumbnailsDefault: Default? = null,
    val medium: Default? = null,
    val high: Default? = null
)

data class Default(
    val url: String? = null,
    val width: Long = 0,
    val height: Long = 0,
)

data class PageInfo(
    val totalResults: Long = 0,
    val resultsPerPage: Long = 0
)