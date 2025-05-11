package org.chat.core.chat.service

import org.chat.core.chat.entity.ChatParticipantEntity
import org.chat.core.chat.entity.RoomEntity
import org.chat.core.chat.enums.RoomType
import org.chat.core.chat.repository.ChatParticipantRepository
import org.chat.core.chat.repository.ChatRoomRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ChatRoomCoreService(
    private val chatRoomRepository: ChatRoomRepository,
    private val chatParticipantRepository: ChatParticipantRepository
) {

    @Transactional
    fun createRoom(sender: Long, receiverId: Long, roomType: RoomType): RoomEntity {
        val room = chatRoomRepository.save(
            RoomEntity(
                roomType = roomType,
                ownerId = sender,
            )
        )

        chatParticipantRepository.saveAll(listOf(
            ChatParticipantEntity(
                roomId = room.identity,
                userId = sender,
            ), ChatParticipantEntity(
                roomId = room.identity,
                userId = receiverId,
            )
        ))

        return room
    }

    @Transactional(readOnly = true)
    fun getRoom(roomId: Long): RoomEntity {
        return chatRoomRepository.findByIdOrNull(roomId)
            ?: throw IllegalArgumentException("Not found roomId: $roomId")
    }
}