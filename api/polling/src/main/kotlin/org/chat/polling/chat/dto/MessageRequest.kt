package org.chat.polling.chat.dto

import org.chat.core.chat.enums.MessageType

data class MessageRequest(
    val message: String,
    val messageType: MessageType,
    val roomId: Long?,
    val receiverId: Long?,
)