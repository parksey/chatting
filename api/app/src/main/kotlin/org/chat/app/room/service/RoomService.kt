package org.chat.app.room.service

import org.chat.core.chat.repository.ChatRoomRepository
import org.chat.app.room.dto.Room
import org.chat.app.room.dto.RoomResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class RoomService(
    private val chatRoomRepository: ChatRoomRepository
) {

    @Transactional(readOnly = true)
    fun getRooms(userId: Long): List<Room> {
        val rooms = chatRoomRepository.findAllByOwnerId(userId)

        return RoomResponse.of(rooms)
    }
}