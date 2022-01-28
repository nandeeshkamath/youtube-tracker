package com.youtube.tracker.exception

import com.youtube.tracker.models.ResultInfo

data class ClientException(val resultInfo: ResultInfo, val info: Any? = null) : RuntimeException()