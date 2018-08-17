package com.marcskow.url.shortener.security

import org.springframework.context.annotation.Configuration
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter
import java.util.*


@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
class SuCorsFilter : CorsFilter(configurationSource()) {

    companion object {
        private fun configurationSource(): UrlBasedCorsConfigurationSource {
            val configuration = CorsConfiguration()
            configuration.allowedOrigins = Arrays.asList("http://localhost:4210")
            configuration.allowedMethods = Arrays.asList("GET", "POST", "OPTIONS")
            configuration.addAllowedHeader("x-requested-with")
            val source = UrlBasedCorsConfigurationSource()
            source.registerCorsConfiguration("/**", configuration)
            return source
        }
    }
}