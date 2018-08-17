package com.marcskow.url.shortener.security.user.details

import com.marcskow.url.shortener.user.UsUser
import org.springframework.security.core.userdetails.UserDetails

class UsUserDetails(private val user: UsUser) : UserDetails {
    override fun getAuthorities() = user.roles.toMutableList()
    override fun isEnabled() = true
    override fun getUsername() = user.username
    override fun isCredentialsNonExpired() = true
    override fun getPassword() = user.password
    override fun isAccountNonExpired() = true
    override fun isAccountNonLocked() = true
}