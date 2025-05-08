package org.chat.polling.chat.dto

data class MessageRequest(
    val message: String,
    val roomId: String,
)