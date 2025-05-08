package org.chat.app.auth.dto

import org.chat.core.user.dto.UserCreateCommand

data class SignUpRequest(
    val loginId: String,
    val name: String,
    val password: String,
) {

    fun of(encodedPassword: String) = UserCreateCommand(
        loginId = loginId,
        encodedPassword = encodedPassword,
        name = name,
    )
}