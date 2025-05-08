package org.chat.app.auth.presentation

import jakarta.servlet.http.HttpServletResponse
import org.chat.app.auth.dto.LoginRequest
import org.chat.app.auth.dto.SignUpRequest
import org.chat.app.auth.service.UserAuthService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController(private val userAuthService: UserAuthService) {

    @PostMapping("/user/sign-up")
    fun signUp(
        response: HttpServletResponse,
        @RequestBody signUpRequest: SignUpRequest
    ): ResponseEntity<Void> {
        userAuthService.signUp(response, signUpRequest)

        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @PostMapping("/login")
    fun login(
        response: HttpServletResponse,
        @RequestBody loginRequest: LoginRequest,
    ): ResponseEntity<Void> {
        userAuthService.login(response, loginRequest)
        return ResponseEntity.ok().build()
    }
}