package com.marcskow.url.shortener.statistics

import com.marcskow.url.shortener.user.User
import org.jetbrains.annotations.NotNull
import javax.persistence.*

@Entity
data class Statistics(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0,
                      @OneToOne(fetch = FetchType.LAZY, mappedBy = "statistics")
                      val user: User,
                      @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
                      @JoinColumn(name = "statistics_id")
                      val statisticsEntry: List<StatisticsEntry>
)

@Entity
data class StatisticsEntry(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0,
                           @NotNull val originalLink: String,
                           @NotNull val shortenedLink: String,
                           val visits: Long = 0)