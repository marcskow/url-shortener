package com.marcskow.url.shortener.security.user.details

import com.marcskow.url.shortener.user.User
import org.springframework.security.core.userdetails.UserDetails

class UsUserDetails(private val user: User) : UserDetails {
    override fun getAuthorities() = user.roles.map { it.role }
    override fun isEnabled() = true
    override fun getUsername() = user.username
    override fun isCredentialsNonExpired() = true
    override fun getPassword() = user.password
    override fun isAccountNonExpired() = true
    override fun isAccountNonLocked() = true
}