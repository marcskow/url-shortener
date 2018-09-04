package com.marcskow.url.shortener.statistics.user

import com.fasterxml.jackson.annotation.JsonIgnore
import com.marcskow.url.shortener.user.User
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import org.jetbrains.annotations.NotNull
import javax.persistence.*

@Entity
data class UserLinkStatistics(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) private val id: Long = 0,
                              @NotNull val originalUrl: String,
                              @NotNull val shortenedUrl: String,
                              var visits: Long = 0,
                              @ManyToOne(fetch = FetchType.EAGER, optional = false)
                              @JoinColumn(name = "user_username", nullable = false)
                              @OnDelete(action = OnDeleteAction.CASCADE) @JsonIgnore
                              private val user: User
)