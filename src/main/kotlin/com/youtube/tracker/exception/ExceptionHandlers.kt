package com.youtube.tracker.exception

import com.youtube.tracker.constants.ResultInfoConstants
import com.youtube.tracker.models.ResponseWrapper
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandlers {

    @ExceptionHandler(HttpMessageNotReadableException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    fun handleHttpMessageNotReadableException(exception: HttpMessageNotReadableException) =
        ResponseWrapper(resultInfo = ResultInfoConstants.BAD_REQUEST, data = exception.message)

    @ExceptionHandler(ClientException::class)
    @ResponseBody
    fun handleClientException(exception: ClientException) =
        ResponseEntity
            .status(exception.status)
            .body(ResponseWrapper(resultInfo = exception.resultInfo, data = exception.info))

    @ExceptionHandler(ServerException::class)
    @ResponseBody
    fun handleServerException(exception: ClientException) =
        ResponseEntity
            .status(exception.status)
            .body(ResponseWrapper(resultInfo = exception.resultInfo, data = exception.info))

    @ExceptionHandler(InvalidException::class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleInvalidException(exception: InvalidException) =
        ResponseWrapper(resultInfo = ResultInfoConstants.SERVER_ERROR, data = exception.reason)

    @ExceptionHandler(Throwable::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    fun handleGlobalException(exception: Throwable) =
        ResponseWrapper(resultInfo = ResultInfoConstants.SERVER_ERROR, data = exception.message)
}
