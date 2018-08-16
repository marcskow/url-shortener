package com.marcskow.url.shortener.user

import com.marcskow.url.shortener.security.user.details.UsUserDetails
import org.springframework.security.core.GrantedAuthority

class UsUser(val id: Long,
             val username: String,
             val password: String,
             val roles: List<UsRole>) {

    fun toUserDetails(): UsUserDetails {
        return UsUserDetails(UsUser(id, username, password, roles))
    }
}

enum class UsRole : GrantedAuthority {
    US_ROLE_USER,
    US_ROLE_ADMIN;

    override fun getAuthority(): String {
        return this.name
    }
}
