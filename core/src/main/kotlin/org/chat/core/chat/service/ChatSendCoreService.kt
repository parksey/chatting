package org.chat.core.chat.service

import org.chat.core.chat.entity.MessageEntity
import org.chat.core.chat.entity.RoomEntity
import org.chat.core.chat.repository.MessageRepository
import org.chat.core.chat.vo.MessageSenderCommand
import org.springframework.stereotype.Service

@Service
class ChatSendCoreService(
    private val messageRepository: MessageRepository
) {

    fun send(messageSenderCommand: MessageSenderCommand, room: RoomEntity) {
        messageRepository.save(MessageEntity(
            roomId = room.identity,
            content = messageSenderCommand.content,
            senderId = messageSenderCommand.sender,
            messageType = messageSenderCommand.messageType,
        ))
    }
}