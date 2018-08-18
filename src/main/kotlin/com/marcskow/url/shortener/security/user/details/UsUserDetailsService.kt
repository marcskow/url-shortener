package com.marcskow.url.shortener.security.user.details

import com.marcskow.url.shortener.user.UserService
import com.marcskow.url.shortener.user.exception.UserNotFoundException
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UsUserDetailsService(private val userService: UserService) : UserDetailsService {
    override fun loadUserByUsername(username: String): UsUserDetails {
        try {
            return userService.fetchUserByUsername(username).toUserDetails()
        } catch (e: UserNotFoundException) {
            throw UsernameNotFoundException(e.message)
        }
    }
}