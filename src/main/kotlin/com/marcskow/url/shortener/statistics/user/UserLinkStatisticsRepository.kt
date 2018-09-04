package com.marcskow.url.shortener.statistics.user

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface UserLinkStatisticsRepository : PagingAndSortingRepository<UserLinkStatistics, Long> {
    fun findByShortenedUrl(shortenedUrl: String): UserLinkStatistics?

    @Query("SELECT s FROM UserLinkStatistics s WHERE s.user.username = :userName AND s.shortenedUrl = :shortenedUrl")
    fun findByUsername(@Param("userName") userName: String, pageable: Pageable): Page<UserLinkStatistics>

    @Query("SELECT s FROM UserLinkStatistics s WHERE s.user.username = :userName AND s.shortenedUrl = :shortenedUrl")
    fun findByUsernameAndShortenedUrl(@Param("userName") userName: String,
                                      @Param("shortenedUrl") shortenedUrl: String): UserLinkStatistics?
}