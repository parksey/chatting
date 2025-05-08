package org.chat.auth.core.service

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class PasswordManager(
    private val passwordEncoder: PasswordEncoder
) {
    fun encode(password: String): String {
        return passwordEncoder.encode(password)
    }

    fun match(requestPassword: String, userPassword: String): Boolean {
        return passwordEncoder.matches(requestPassword, userPassword)
    }
}