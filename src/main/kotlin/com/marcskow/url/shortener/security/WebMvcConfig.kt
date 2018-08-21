package com.marcskow.url.shortener.security

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
@EnableWebMvc
class WebMvcConfig : WebMvcConfigurer {

    private val MAX_AGE_SECS = 3600L

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("HEAD", "GET", "POST", "PUT", "PATCH", "DELETE")
                .allowedHeaders("Authorization", "Cache-Control", "Content-Type", "X-XSRF-TOKEN")
                .maxAge(MAX_AGE_SECS)
    }
}