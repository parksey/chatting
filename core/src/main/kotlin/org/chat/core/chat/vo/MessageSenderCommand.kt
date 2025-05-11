package org.chat.core.chat.vo

import org.chat.core.chat.enums.MessageType

data class MessageSenderCommand(
    val sender: Long,
    val roomId: Long?,
    val content: String,
    val messageType: MessageType
) {

    companion object {
        fun of(sender: Long, roomId: Long?, content: String, messageType: MessageType): MessageSenderCommand {
            return MessageSenderCommand(
                sender = sender,
                roomId = roomId,
                content = content,
                messageType = messageType,
            )
        }
    }
}