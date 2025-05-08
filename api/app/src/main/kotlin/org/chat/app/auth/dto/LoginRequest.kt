package org.chat.app.auth.dto

data class LoginRequest(
    val loginId: String,
    val password: String,
)