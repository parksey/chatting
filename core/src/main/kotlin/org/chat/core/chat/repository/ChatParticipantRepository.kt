package org.chat.core.chat.repository

import org.chat.core.chat.entity.ChatParticipantEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ChatParticipantRepository : JpaRepository<ChatParticipantEntity, Long> {
}