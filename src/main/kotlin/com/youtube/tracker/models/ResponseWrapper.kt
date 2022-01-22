package com.youtube.tracker.models

import com.youtube.tracker.constants.ResultInfoConstants

data class ResponseWrapper<T>(
    val resultInfo: ResultInfo,
    val data: T?
) {
    companion object {
        fun <T> success(data: T? = null) = ResponseWrapper(ResultInfoConstants.SUCCESS, data)
    }
}