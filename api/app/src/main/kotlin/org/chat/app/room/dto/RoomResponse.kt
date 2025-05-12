package org.chat.app.room.dto

import org.chat.core.chat.entity.RoomEntity

data class RoomResponse(
    val rooms: List<Room>
) {
    companion object {
        fun of(rooms: List<RoomEntity>): List<Room> {
            return rooms.map { with(it){
                Room(
                    id = identity,
                    name = name,
                )
            } }
        }
    }
}

data class Room (
    val id: Long,
    val name: String
)