package com.youtube.tracker.exception

import com.youtube.tracker.constants.ResultInfoConstants
import com.youtube.tracker.models.ResponseWrapper
import org.springframework.http.HttpStatus
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class ExceptionHandlers {

    @ExceptionHandler(HttpMessageNotReadableException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    fun handleHttpMessageNotReadableException(exception: HttpMessageNotReadableException) =
        ResponseWrapper(resultInfo = ResultInfoConstants.BAD_REQUEST, data = exception.message)

    @ExceptionHandler(Throwable::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    fun handleGlobalException(exception: Throwable) =
        ResponseWrapper(resultInfo = ResultInfoConstants.SERVER_ERROR, data = exception.message)
}