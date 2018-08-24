package com.marcskow.url.shortener.statistics

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface UserLinkStatisticsRepository : CrudRepository<UserLinkStatistics, Long> {
    @Query("SELECT s FROM UserLinkStatistics s WHERE v.user.username = :userName AND v.shortenedUrl = :shortenedUrl")
    fun findByUsernameAndShortenedLink(@Param("userName") userName: String,
                                       @Param("shortenedUrl") shortenedUrl: String): UserLinkStatistics?
}