package org.chat.core.chat.entity

import jakarta.persistence.*

@Entity
@Table(name = "room")
class RoomEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    name: String,
) {

    var name: String = name
        private set
}