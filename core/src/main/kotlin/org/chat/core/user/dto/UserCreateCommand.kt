package org.chat.core.user.dto

import org.chat.core.user.entity.UserEntity

data class UserCreateCommand(
    val loginId: String,
    val encodedPassword: String,
    val name: String,
) {

    fun toUser(): UserEntity {
        return UserEntity(
            loginId = loginId,
            password = encodedPassword,
            name = name,
        )
    }
}