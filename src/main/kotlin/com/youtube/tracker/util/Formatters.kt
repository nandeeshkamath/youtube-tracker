package com.youtube.tracker.util

import java.time.ZoneId
import java.time.format.DateTimeFormatter

object Formatters {
    val INSTANT_ISO_FORMATTER: DateTimeFormatter =
        DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'").withZone(ZoneId.of("UTC"))
}