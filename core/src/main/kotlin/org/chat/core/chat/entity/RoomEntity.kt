package org.chat.core.chat.entity

import jakarta.persistence.*
import org.chat.core.chat.enums.RoomType

@Entity
@Table(name = "room")
class RoomEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    name: String? = null,
    roomType: RoomType,
    ownerId: Long,
) {

    var name: String? = name
        private set

    val identity: Long get() = requireNotNull(id)
}