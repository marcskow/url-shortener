package com.marcskow.url.shortener.security

import com.marcskow.url.shortener.security.user.details.UsUserDetails
import io.jsonwebtoken.*
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.security.SignatureException
import java.time.ZonedDateTime
import java.util.*


@Component
class JwtTokenProvider {

    companion object {
        private val log = LoggerFactory.getLogger(JwtTokenProvider::class.java)
    }

    @Value("\${app.jwtSecret}")
    private val jwtSecret: String? = null

    @Value("\${app.jwtExpirationInMs}")
    private val jwtExpirationInMs: Int = 0

    fun generateToken(authentication: Authentication): String {

        val userPrincipal = authentication.principal as UsUserDetails

        val now = ZonedDateTime.now()
        val expirationDate = now.plusSeconds(jwtExpirationInMs / 1000L)

        return Jwts.builder()
                .setSubject(userPrincipal.username)
                .setIssuedAt(Date.from(now.toInstant()))
                .setExpiration(Date.from(expirationDate.toInstant()))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact()
    }

    fun getUsernameFromJWT(token: String): String {
        val claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .body

        return claims.subject
    }

    fun validateToken(authToken: String): Boolean {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken)
            return true
        } catch (ex: SignatureException) {
            log.error("Invalid JWT signature")
        } catch (ex: MalformedJwtException) {
            log.error("Invalid JWT token")
        } catch (ex: ExpiredJwtException) {
            log.error("Expired JWT token")
        } catch (ex: UnsupportedJwtException) {
            log.error("Unsupported JWT token")
        } catch (ex: IllegalArgumentException) {
            log.error("JWT claims string is empty.")
        }

        return false
    }
}