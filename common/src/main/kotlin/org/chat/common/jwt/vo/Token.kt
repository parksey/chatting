package org.chat.common.jwt.vo

import java.util.Date

data class Token(
    val grantType: GrantType,
    val accessToken: String,
    val accessTokenExpire: Date,
    val refreshToken: String,
    val refreshTokenExpire: Date,
)