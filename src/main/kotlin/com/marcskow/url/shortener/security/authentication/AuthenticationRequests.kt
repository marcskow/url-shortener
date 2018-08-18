package com.marcskow.url.shortener.security.authentication

import javax.validation.constraints.NotBlank

data class LoginRequest(@NotBlank val username: String,
                        @NotBlank val password: String)

data class SignUpRequest(val firstName: String = "",
                         val lastName: String = "",
                         @NotBlank val username: String,
                         @NotBlank val password: String)