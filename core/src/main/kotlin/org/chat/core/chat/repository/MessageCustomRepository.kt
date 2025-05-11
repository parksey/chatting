package org.chat.core.chat.repository

import org.chat.core.chat.vo.MessageInfo

interface MessageCustomRepository {

    fun findMessagesBy(roomId: Long, cursorId: Long?, limit: Long): List<MessageInfo>
}