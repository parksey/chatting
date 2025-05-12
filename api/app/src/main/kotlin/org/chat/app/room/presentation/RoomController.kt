package org.chat.app.room.presentation

import org.chat.auth.core.vo.CurrentUser
import org.chat.app.room.dto.Room
import org.chat.app.room.service.RoomService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class RoomController(private val roomService: RoomService) {

    @GetMapping("/rooms")
    fun getRooms(
        @CurrentUser user: Long
    ): ResponseEntity<List<Room>> {
        return ResponseEntity.ok(roomService.getRooms(user))
    }
}