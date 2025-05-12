package org.chat.polling.chat.service

import org.chat.core.chat.enums.RoomType
import org.chat.core.chat.repository.MessageCustomRepository
import org.chat.core.chat.service.ChatRoomCoreService
import org.chat.core.chat.service.ChatSendCoreService
import org.chat.core.chat.vo.MessageSenderCommand
import org.chat.polling.chat.dto.MessagesResponse
import org.springframework.stereotype.Service

@Service
class ChatService(
    private val chatRoomCoreService: ChatRoomCoreService,
    private val chatSendCoreService: ChatSendCoreService,
    private val messageCustomRepository: MessageCustomRepository,
) {
    fun send(messageCommand: MessageSenderCommand, receiverId: Long?) {
        val room = when {
            messageCommand.roomId == null && receiverId != null->
                chatRoomCoreService.createRoom(messageCommand.sender, receiverId, RoomType.INDIVIDUAL)
            messageCommand.roomId != null  && receiverId == null ->
                chatRoomCoreService.getRoom(messageCommand.roomId!!)
            else -> throw IllegalArgumentException("roomId or receiverId must not be null")
        }

        chatSendCoreService.send(messageCommand, room)
    }

    fun getMessages(user: Long, roomId: Long, cursorId: Long?, limit: Long): MessagesResponse {
        val messageInfos = messageCustomRepository.findMessagesBy(
            roomId = roomId,
            cursorId = cursorId,
            limit = limit,
        )

        return MessagesResponse.of(messageInfos, user)
    }
}