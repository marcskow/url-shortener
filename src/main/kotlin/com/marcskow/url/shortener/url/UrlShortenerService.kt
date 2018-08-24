package com.marcskow.url.shortener.url

import com.marcskow.url.shortener.statistics.UserLinkStatistics
import com.marcskow.url.shortener.statistics.UserLinkStatisticsRepository
import com.marcskow.url.shortener.user.User
import org.springframework.stereotype.Service

@Service
class UrlShortenerService(private val urlRepository: UrlRepository,
                          private val userLinkStatisticsRepository: UserLinkStatisticsRepository,
                          private val idEncoder: IdEncoder) {

    fun computeShortenedUrl(user: User, originalUrl: String): String {
        urlRepository.findByUrl(originalUrl)?.let {
            return idEncoder.encode(it.id)
        }

        val url = urlRepository.save(Url(url = originalUrl))
        val shortend = idEncoder.encode(url.id)
        val statistics = UserLinkStatistics(originalLink = originalUrl, shortenedLink = shortend, user = user)
        userLinkStatisticsRepository.save(statistics)

        return idEncoder.encode(url.id)
    }

    fun fetchOriginalUrl(user: User, shortenedUrl: String): String {
        val id = idEncoder.decode(shortenedUrl)
        val url = urlRepository.findById(id).map { it.url }.orElse("")
        url?.let { userLinkStatisticsRepository.findByUsernameAndShortenedLink(user.username, shortenedUrl)?.visits?.inc() }
        return url
    }
}