package com.youtube.tracker.models.request

import com.youtube.tracker.enums.ResultType
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

data class TrackerRequest(
    val keyword: @NotNull Keyword,
    val interval: @Positive Long?,
    val resultType: ResultType = ResultType.VIDEO,
    val channels: Set<String> = emptySet(),
    val targetChannel: String?,
)

data class Keyword(
    val core: @NotBlank String,
    val extras: Set<String> = emptySet()
) {
    val all: Set<String>
        get() = this.extras.plus(core)
}
