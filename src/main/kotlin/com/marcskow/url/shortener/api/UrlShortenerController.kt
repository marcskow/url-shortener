package com.marcskow.url.shortener.api

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/shorten")
class UrlShortenerController {

    data class ShortenedUrl(val shortened: String)

    data class ShortenUrlRequest(val url: String)

    @PostMapping
    fun shortenUrl(@RequestBody shortenUrlRequest: ShortenUrlRequest) {

    }
}