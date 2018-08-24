package com.marcskow.url.shortener.api

import com.marcskow.url.shortener.user.UserRepository
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/user")
class UserController(private val userRepository: UserRepository) {

    data class UserDetailsResponse(val username: String, val firstName: String, val lastName: String)

    @GetMapping
    fun getUserDetails(authentication: Authentication): UserDetailsResponse {
        val user = userRepository.findByUsername(authentication.name) ?: throw UsernameNotFoundException("User with given username not found")
        return UserDetailsResponse(user.username, user.firstName, user.lastName)
    }

}