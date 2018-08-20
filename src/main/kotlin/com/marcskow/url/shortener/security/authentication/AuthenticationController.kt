package com.marcskow.url.shortener.security.authentication

import com.marcskow.url.shortener.security.JwtTokenProvider
import com.marcskow.url.shortener.user.UsRole
import com.marcskow.url.shortener.user.UsUser
import com.marcskow.url.shortener.user.UserRepository
import com.marcskow.url.shortener.user.UserService
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.servlet.support.ServletUriComponentsBuilder





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
        if(userRepository.existsByUsername(signUpRequest.username)) {
            return ResponseEntity(ApiResponse(false, "Username is already taken!"), HttpStatus.BAD_REQUEST)
        }

        val password = passwordEncoder.encode(signUpRequest.password)
        val user = UsUser(signUpRequest.username, password, listOf(UsRole.US_ROLE_USER))

        val result = userRepository.save(user.toUserEntity())
        val location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/api/users/{username}")
                .buildAndExpand(result.username).toUri()
        return ResponseEntity.created(location).body(ApiResponse(true, "User registered successfully"))
    }

}