package com.marcskow.url.shortener.security.user.details

import com.marcskow.url.shortener.user.UserService
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UsUserDetailsService(private val userService: UserService) : UserDetailsService {
    override fun loadUserByUsername(username: String) = userService.fetchUserByUsername(username).toUserDetails()
}