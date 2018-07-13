package com.marcskow.url.shortener.api

import com.marcskow.url.shortener.exception.EmptyNameException
import com.marcskow.url.shortener.exception.ErrorResponse
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.ZonedDateTime

@RestController
@RequestMapping("/api/hello")
class HelloController {

    companion object {
        private val log = LoggerFactory.getLogger(HelloController::class.java)
    }

    data class HelloResponse(val message: String, val name: String)

    @GetMapping("/{name}")
    fun getHelloMessage(@PathVariable name: String): HelloResponse {
        return HelloResponse("Hello, $name", name)
    }

    @ExceptionHandler(value = [EmptyNameException::class])
    fun handleException(exception: Exception): ResponseEntity<ErrorResponse> {
        log.error("HelloMessage for empty name requested ${ZonedDateTime.now()}")
        return ResponseEntity(ErrorResponse(404, "Name is empty"), HttpStatus.NOT_FOUND)
    }
}