package com.marcskow.url.shortener.url

import com.marcskow.url.shortener.statistics.user.UserLinkStatistics
import com.marcskow.url.shortener.statistics.user.UserLinkStatisticsRepository
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
        val statistics = UserLinkStatistics(originalUrl = originalUrl, shortenedUrl = shortend, user = user)
        userLinkStatisticsRepository.save(statistics)

        return idEncoder.encode(url.id)
    }

    fun fetchOriginalUrl(shortenedUrl: String): String {
        val id = idEncoder.decode(shortenedUrl)
        val url = urlRepository.findById(id).map { it.url }.orElse("")
        url?.let { userLinkStatisticsRepository.findByShortenedUrl(shortenedUrl)?.visits?.inc() }
        return url
    }
}