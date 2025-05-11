package org.chat.polling.chat.controller

import org.chat.auth.core.vo.CurrentUser
import org.chat.core.chat.service.ChatSendCoreService
import org.chat.core.chat.vo.MessageSenderCommand
import org.chat.polling.chat.dto.MessageRequest
import org.chat.polling.chat.dto.MessagesResponse
import org.chat.polling.chat.service.ChatService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class ChatController(
    private val chatService: ChatService
) {

    @PostMapping("/chat/message")
    fun send(
        @CurrentUser user: Long,
        @RequestBody request: MessageRequest,
    ): ResponseEntity<Void> {
        chatService.send(MessageSenderCommand.of(
            sender = user,
            roomId = request.roomId,
            content = request.message,
            messageType = request.messageType,
        ), request.receiverId)

        return ResponseEntity.ok().build();
    }

    @GetMapping("/chat/{roomId}/messages")
    fun getMessages(
        @PathVariable roomId: Long,
        @RequestParam cursorId: Long? = null,
        @RequestParam limit: Long = 20,
    ): ResponseEntity<MessagesResponse> {
        return ResponseEntity.ok(chatService.getMessages(roomId, cursorId, limit))
    }
}