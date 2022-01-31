package com.youtube.tracker.models.request

import com.youtube.tracker.enums.ResultType
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Positive

data class TrackerRequest (
    val keywords: @NotEmpty Set<String>,
    val interval: @Positive Long?,
    val resultType: ResultType = ResultType.VIDEO,
    val channels: Set<String> = emptySet(),
    val targetChannel: String?,
) {
    fun concatenateKeywords(separator: String = " ") =
        this.keywords.joinToString(separator)
}
