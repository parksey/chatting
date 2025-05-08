package org.chat.common.jwt.vo

enum class GrantType(val type: String) {
    BEARER("Bearer");

    val typeWithSpace get() = "$type "
}