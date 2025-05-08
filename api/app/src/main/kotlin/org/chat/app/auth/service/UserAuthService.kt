package org.chat.app.auth.service

import jakarta.servlet.http.HttpServletResponse
import org.chat.app.auth.dto.LoginRequest
import org.chat.app.auth.dto.SignUpRequest
import org.chat.auth.core.service.AuthorizationService
import org.chat.auth.core.service.PasswordManager
import org.chat.common.jwt.vo.PublicClaim
import org.chat.core.user.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserAuthService(
    private val passwordManager: PasswordManager,
    private val userRepository: UserRepository,
    private val authorizationService: AuthorizationService
) {

    @Transactional
    fun signUp(response: HttpServletResponse, signUpRequest: SignUpRequest) {
        if (userRepository.existsByLoginId(signUpRequest.loginId)) {
            throw IllegalArgumentException("Already exists loginId: ${signUpRequest.loginId}")
        }

        val encodedPassword = passwordManager.encode(signUpRequest.password)
        val userCreateCommand = signUpRequest.of(encodedPassword)

        val user = userRepository.save(userCreateCommand.toUser())

        authorizationService.provideNewToken(
            response = response,
            publicClaim = PublicClaim(
                id = user.identity
            )
        )
    }

    @Transactional(readOnly = true)
    fun login(response: HttpServletResponse, loginRequest: LoginRequest) {
        val user = userRepository.findByLoginId(loginRequest.loginId)
            ?: throw IllegalArgumentException("Not found loginId: ${loginRequest.loginId}")

        validLogin(loginRequest.password, user.password)

        authorizationService.provideNewToken(
            response = response,
            publicClaim = PublicClaim(
                id = user.identity
            )
        )
    }

    private fun validLogin(password: String, encodedPassword: String) {
        if (!passwordManager.match(password, encodedPassword)) {
            throw IllegalArgumentException("Invalid password")
        }
    }
}