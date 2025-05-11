package org.chat.core.user.entity

import jakarta.persistence.*
import org.chat.core.common.BaseEntity

@Entity
@Table(name = "user")
class UserEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val loginId: String,
    val password: String,
    val name: String,
    val thumbnail: String? = null,
) : BaseEntity() {

    val identity: Long get() = requireNotNull(id)
}