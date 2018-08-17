package com.marcskow.url.shortener.security

import com.marcskow.url.shortener.security.user.details.UsUserDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.csrf.CookieCsrfTokenRepository


@Configuration
class WebSecurityConfiguration : WebSecurityConfigurerAdapter() {

    @Autowired
    private lateinit var userDetailsService: UsUserDetailsService


    @Throws(Exception::class)
    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetailsService)
    }

    override fun configure(http: HttpSecurity) {
        http
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/index.html", "/", "/home", "/login").permitAll()
                .anyRequest().authenticated()
                .and().csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
    }
}