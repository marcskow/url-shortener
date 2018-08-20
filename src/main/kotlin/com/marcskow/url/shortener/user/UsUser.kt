package com.marcskow.url.shortener.user

import com.marcskow.url.shortener.security.user.details.UsUserDetails
import org.springframework.security.core.GrantedAuthority

class UsUser(val username: String,
             val password: String,
             val roles: List<UsRole>) {

    fun toUserDetails(): UsUserDetails {
        return UsUserDetails(UsUser(username, password, roles))
    }

    fun toUserEntity(): UserEntity {
        return UserEntity(username = username, password = password, roles = roles.map { RoleEntity(role = it.name) })
    }
}

enum class UsRole : GrantedAuthority {
    US_ROLE_USER,
    US_ROLE_ADMIN;

    override fun getAuthority(): String {
        return this.name
    }
}
