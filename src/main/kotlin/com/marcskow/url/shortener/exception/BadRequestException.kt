package com.marcskow.url.shortener.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.BAD_REQUEST)
class BadRequestException(override val message: String, override val cause: Throwable?) : Exception(message, cause) {
    constructor(message: String) : this(message, null)
}