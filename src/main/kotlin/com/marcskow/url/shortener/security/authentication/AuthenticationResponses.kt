package com.marcskow.url.shortener.security.authentication

data class JwtAuthenticationResponse(val accessToken: String, val tokenType: String = "Bearer")

data class ApiResponse(val success: Boolean, val message: String)