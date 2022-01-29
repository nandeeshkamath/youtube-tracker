package com.youtube.tracker.exception

import com.youtube.tracker.models.ResultInfo

data class ClientException(val status: Int, val resultInfo: ResultInfo, val info: Any? = null) : RuntimeException()
data class ServerException(val status: Int, val resultInfo: ResultInfo, val info: Any? = null) : RuntimeException()
class InvalidException(val reason: String) : RuntimeException()