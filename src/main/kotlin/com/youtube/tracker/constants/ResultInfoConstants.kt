package com.youtube.tracker.constants

import com.youtube.tracker.models.ResultInfo

object ResultInfoConstants {
    val SUCCESS = ResultInfo("000", "SUCCESS")
    val BAD_REQUEST = ResultInfo("001", "BAD_REQUEST")
    val SERVER_ERROR = ResultInfo("999", "SERVER_ERROR")
}