package org.chat.polling.chat.dto

import org.chat.core.chat.enums.MessageType
import org.chat.core.chat.vo.MessageInfo
import org.chat.polling.chat.service.MessageFilter
import java.time.LocalDateTime

data class MessagesResponse (
    val messages: List<Message>,
) {
    companion object {
        fun of(messageInfos: List<MessageInfo>): MessagesResponse {
            return MessagesResponse(
                messages = messageInfos.map { Message.of(it) }
            )
        }
    }
}

data class Message (
    val id: Long,
    val content: String,
    val sender: Sender,
    val messageType: MessageType,
    val createdAt: LocalDateTime,
    val isDeleted: Boolean,
) {
    companion object {
        fun of(messageInfo: MessageInfo): Message {
            return with(messageInfo) {
                Message(
                    id = id,
                    content = MessageFilter.filter(content, isDeleted),
                    sender = Sender(
                        id = userId,
                        name = userName,
                        thumbnail = thumbnail
                    ),
                    messageType = messageType,
                    createdAt = createdAt,
                    isDeleted = isDeleted
                )
            }
        }
    }
}

data class Sender (
    val id: Long,
    val name: String,
    val thumbnail: String?,
)