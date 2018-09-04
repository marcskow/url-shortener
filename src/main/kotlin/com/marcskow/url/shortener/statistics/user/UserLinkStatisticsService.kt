package com.marcskow.url.shortener.statistics.user

import com.marcskow.url.shortener.general.PageableList
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class UserLinkStatisticsService(private val userLinkStatisticsRepository: UserLinkStatisticsRepository) {
    fun fetchUserStatistics(username: String, pageRequest: PageRequest): PageableList<UserLinkStatistics> {
        val statistics = userLinkStatisticsRepository.findByUsername(username, pageRequest)
        return PageableList(statistics.content, statistics.totalElements)
    }
}