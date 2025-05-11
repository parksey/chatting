package org.chat.core.chat.vo

import org.chat.core.chat.enums.MessageType
import java.time.LocalDateTime

data class MessageInfo(
    val id: Long,
    val content: String,
    val messageType: MessageType,
    val isDeleted: Boolean,
    val createdAt: LocalDateTime,
    val userId: Long,
    val userName: String,
    val thumbnail: String?
) {
}