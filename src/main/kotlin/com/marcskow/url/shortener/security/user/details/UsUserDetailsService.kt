package com.marcskow.url.shortener.security.user.details

import com.marcskow.url.shortener.user.UserRepository
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UsUserDetailsService(private val userRepository: UserRepository) : UserDetailsService {
    override fun loadUserByUsername(username: String): UsUserDetails {
        val user = userRepository.findByUsername(username) ?: throw UsernameNotFoundException("User not found!");
        return UsUserDetails(user)
    }
}