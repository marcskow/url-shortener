package com.marcskow.url.shortener.api

import com.marcskow.url.shortener.url.UrlShortenerService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletResponse


@RestController
@RequestMapping("/u/{encoded}")
class OriginalLinkController(private val urlShortenerService: UrlShortenerService) {

    @GetMapping
    fun getOriginalUrl(@PathVariable encoded: String, httpServletResponse: HttpServletResponse) {
        val url = urlShortenerService.fetchOriginalUrl(encoded)
        httpServletResponse.sendRedirect(url)
    }
}