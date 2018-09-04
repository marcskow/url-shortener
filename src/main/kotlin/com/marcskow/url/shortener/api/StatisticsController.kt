package com.marcskow.url.shortener.api

import com.marcskow.url.shortener.statistics.app.AppStatistics
import com.marcskow.url.shortener.statistics.app.AppStatisticsService
import com.marcskow.url.shortener.statistics.user.UserLinkStatisticsService
import org.springframework.data.domain.PageRequest
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/statistics")
class StatisticsController(private val userLinkStatisticsService: UserLinkStatisticsService,
                           private val appStatisticsService: AppStatisticsService) {

    data class UserStatisticsResponse(val result: List<UserStatisticsEntry>, val total: Long)

    data class UserStatisticsEntry(val original: String, val shortened: String, val visits: Long)

    @GetMapping("/user")
    fun getUserStatistics(authentication: Authentication,
                          @RequestParam(defaultValue = "0") page: Int,
                          @RequestParam(defaultValue = "20") size: Int): UserStatisticsResponse {
        val userStatistics = userLinkStatisticsService.fetchUserStatistics(authentication.name, PageRequest.of(page, size))
        return UserStatisticsResponse(userStatistics.result.map { UserStatisticsEntry(it.originalUrl, it.shortenedUrl, it.visits) }, userStatistics.total)
    }

    @GetMapping("/app")
    fun getAppStatistics(): AppStatistics {
        return appStatisticsService.fetchAppStatistics()
    }
}