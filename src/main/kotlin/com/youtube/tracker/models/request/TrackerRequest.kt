package com.youtube.tracker.models.request

import com.youtube.tracker.enums.PartType
import javax.validation.constraints.NotBlank

class TrackerRequest (
    val keyword: @NotBlank String,
    val interval: Long?,
    val type: PartType = PartType.SNIPPET,
    val channels: Set<String> = emptySet(),
    val targetChannel: String?,
)