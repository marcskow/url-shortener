package com.marcskow.url.shortener.user

import com.marcskow.url.shortener.user.exception.UserNotFoundException
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {

    fun fetchUserByUsername(username: String): UsUser {
        val userEntity = userRepository.findByUsername(username)
        userEntity ?: throw UserNotFoundException("User not found $username")
        return UsUser(userEntity.username, userEntity.password, userEntity.roles.map { UsRole.valueOf(it.role) })
    }
}