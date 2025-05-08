package org.chat.core.user.repository

import org.chat.core.user.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<UserEntity, Long> {
    fun findByLoginId(loginId: String): UserEntity?
    fun existsByLoginId(loginId: String): Boolean
}