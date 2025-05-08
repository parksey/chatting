package org.chat.core.chat.entity

import jakarta.persistence.*
import org.chat.core.common.BaseEntity

@Table(name = "char_room_user")
@Entity
class ChatParticipantEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val roomId: Long,
    val userId: Long,
) : BaseEntity() {
}