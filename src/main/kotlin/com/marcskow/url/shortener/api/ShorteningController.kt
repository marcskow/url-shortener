package com.marcskow.url.shortener.api

import com.marcskow.url.shortener.url.UrlShortenerService
import com.marcskow.url.shortener.user.UserRepository
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.InetAddress

@RestController
@RequestMapping("/api/shorten")
class ShorteningController(private val userRepository: UserRepository,
                           private val urlShortenerService: UrlShortenerService) {

    data class ShortenUrlRequest(val url: String)

    @PostMapping
    fun shortenUrl(authentication: Authentication, @RequestBody shortenUrlRequest: ShortenUrlRequest): String {
        val user = userRepository.findByUsername(authentication.name)
                ?: throw UsernameNotFoundException("User with given username not found")
        val url = shortenUrlRequest.url
        val httpUrl = if (url.startsWith("http", true)) url else "http://$url"
        return "http://${InetAddress.getLocalHost().hostAddress}:8080/u/${urlShortenerService.computeShortenedUrl(user, httpUrl)}"
    }
}