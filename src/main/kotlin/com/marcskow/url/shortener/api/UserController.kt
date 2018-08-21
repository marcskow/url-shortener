package com.marcskow.url.shortener.api

import com.marcskow.url.shortener.user.UserService
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/user")
class UserController(private val userService: UserService) {

    data class UserDetailsResponse(val username: String, val firstName: String, val lastName: String)

    @GetMapping
    fun getUserDetails(authentication: Authentication): UserDetailsResponse {
        val user = userService.fetchUserByUsername(authentication.name)
        return UserDetailsResponse(user.username, user.firstName, user.lastName)
    }

}