package com.marcskow.url.shortener.security.authentication

import com.marcskow.url.shortener.security.JwtTokenProvider
import com.marcskow.url.shortener.user.Role
import com.marcskow.url.shortener.user.RoleValue
import com.marcskow.url.shortener.user.User
import com.marcskow.url.shortener.user.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import javax.validation.Valid


@RestController
@RequestMapping("/api/auth")
class AuthenticationController(val authenticationManager: AuthenticationManager,
                               val userRepository: UserRepository,
                               val passwordEncoder: PasswordEncoder,
                               val jwtTokenProvider: JwtTokenProvider) {

    @PostMapping("/login")
    fun authenticateUser(@Valid @RequestBody loginRequest: LoginRequest): ResponseEntity<Any> {
        val authentication = authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(
                        loginRequest.username,
                        loginRequest.password
                )
        )

        SecurityContextHolder.getContext().authentication = authentication
        val jwt = jwtTokenProvider.generateToken(authentication)
        return ResponseEntity.ok(JwtAuthenticationResponse(jwt))
    }

    @PostMapping("/register")
    fun registerUser(@Valid @RequestBody signUpRequest: SignUpRequest): ResponseEntity<ApiResponse> {
        if (userRepository.existsByUsername(signUpRequest.username)) {
            return ResponseEntity(ApiResponse(false, "Username is already taken!"), HttpStatus.BAD_REQUEST)
        }

        val password = passwordEncoder.encode(signUpRequest.password)
        val user = User(
                username = signUpRequest.username,
                firstName = signUpRequest.firstName,
                lastName = signUpRequest.lastName,
                password = password,
                roles = listOf(Role(role = RoleValue.USER)))

        val result = userRepository.save(user)
        val location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/api/users/{username}")
                .buildAndExpand(result.username).toUri()
        return ResponseEntity.created(location).body(ApiResponse(true, "User registered successfully"))
    }

    @GetMapping("/logout")
    fun logout(authentication: Authentication): ResponseEntity<Any> {
        return ResponseEntity.ok("User logged out")
    }
}