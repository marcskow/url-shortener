package com.marcskow.url.shortener.statistics

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface UserLinkStatisticsRepository : CrudRepository<UserLinkStatistics, Long> {
    fun findByShortenedUrl(shortenedUrl: String): UserLinkStatistics?

    @Query("SELECT s FROM UserLinkStatistics s WHERE s.user.username = :userName AND s.shortenedUrl = :shortenedUrl")
    fun findByUsernameAndShortenedUrl(@Param("userName") userName: String,
                                      @Param("shortenedUrl") shortenedUrl: String): UserLinkStatistics?
}