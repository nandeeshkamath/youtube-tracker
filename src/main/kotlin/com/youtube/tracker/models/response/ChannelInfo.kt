package com.youtube.tracker.models.response

import com.fasterxml.jackson.annotation.JsonProperty

data class ChannelInfo(
    val ok: Boolean,
    @field:JsonProperty("error_code") val errorCode: Int?,
    val description: String?,
    val result: Any?
)
