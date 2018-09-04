package com.marcskow.url.shortener.statistics.app

import com.marcskow.url.shortener.statistics.user.UserLinkStatisticsRepository
import org.springframework.stereotype.Service

@Service
class AppStatisticsService(private val userLinkStatisticsRepository: UserLinkStatisticsRepository) {
    fun fetchAppStatistics(): AppStatistics {
        val shortenedLinks = userLinkStatisticsRepository.count()
        return AppStatistics(shortenedLinks)
    }
}