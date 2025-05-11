package org.chat.polling.chat.service

object MessageFilter {
    private val DELETED: String = "삭제된 메세지입니다."

    fun filter(message: String, isDeleted: Boolean): String {
        return if (isDeleted) DELETED else message
    }
}