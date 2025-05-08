package org.chat.core.user

import jakarta.persistence.*
import org.chat.core.common.BaseEntity

@Entity
@Table(name = "user")
class UserEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val name: String,
) : BaseEntity() {
}