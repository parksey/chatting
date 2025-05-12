package org.chat.core.chat.repository

import org.chat.core.chat.entity.RoomEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ChatRoomRepository : JpaRepository<RoomEntity, Long> {
    fun findAllByOwnerId(userId: Long): List<RoomEntity>
}