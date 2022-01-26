package com.youtube.tracker.models.request

import com.youtube.tracker.enums.PartType
import com.youtube.tracker.enums.ResultType
import javax.validation.constraints.NotBlank

data class TrackerRequest (
    val keyword: @NotBlank String,
    val interval: Long?,
    val type: PartType = PartType.SNIPPET,
    val resultType: ResultType = ResultType.VIDEO,
    val channels: Set<String> = emptySet(),
    val targetChannel: String?,
)