package org.chat.core.chat.service

import org.chat.core.chat.entity.ChatParticipantEntity
import org.chat.core.chat.entity.RoomEntity
import org.chat.core.chat.enums.RoomType
import org.chat.core.chat.repository.ChatParticipantRepository
import org.chat.core.chat.repository.ChatRoomRepository
import org.chat.core.user.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ChatRoomCoreService(
    private val chatRoomRepository: ChatRoomRepository,
    private val chatParticipantRepository: ChatParticipantRepository,
    private val userRepository: UserRepository
) {

    @Transactional
    fun createRoom(sender: Long, receiverId: Long, roomType: RoomType): RoomEntity {
        val receiver = userRepository.findByIdOrNull(receiverId)
            ?: throw IllegalArgumentException("Not found receiverId: $receiverId")

        val room = chatRoomRepository.save(
            RoomEntity(
                roomType = roomType,
                ownerId = sender,
                name = receiver.name,
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