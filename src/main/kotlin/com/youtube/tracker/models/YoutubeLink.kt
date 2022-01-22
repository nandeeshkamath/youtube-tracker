package com.youtube.tracker.models

data class YoutubeLink(val videoId: String) {
    fun get() = "https://youtu.be/$videoId"
}