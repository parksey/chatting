package org.chat.core.chat.entity

import jakarta.persistence.*
import org.chat.core.chat.enums.MessageType
import org.chat.core.common.BaseEntity

@Entity
@Table(name = "message")
class MessageEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val roomId: Long,
    val content: String,
    val senderId: Long,
    @Enumerated(EnumType.STRING)
    val messageType: MessageType,
    val isDeleted: Boolean = false,
) : BaseEntity() {
}