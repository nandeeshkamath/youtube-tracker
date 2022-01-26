package com.youtube.tracker.models.request

import com.youtube.tracker.enums.PartType
import com.youtube.tracker.enums.ResultType
import javax.validation.constraints.NotEmpty

data class TrackerRequest (
    val keywords: @NotEmpty Set<String>,
    val interval: Long?,
    val type: PartType = PartType.SNIPPET,
    val resultType: ResultType = ResultType.VIDEO,
    val channels: Set<String> = emptySet(),
    val targetChannel: String?,
)