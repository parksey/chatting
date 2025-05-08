package org.chat.common.config

import io.jsonwebtoken.security.Keys
import org.springframework.boot.context.properties.ConfigurationProperties
import java.nio.charset.StandardCharsets
import java.security.Key

@ConfigurationProperties(prefix = "key")
data class KeyProperty(
    val iss: String,
    val accessExpire: Long,
    val refreshExpire: Long,
    val accessSecret: String,
    val refreshSecret: String,
) {
    val accessKey: Key = Keys.hmacShaKeyFor(accessSecret.toByteArray(StandardCharsets.UTF_8))
    val secretKey: Key = Keys.hmacShaKeyFor(refreshSecret.toByteArray(StandardCharsets.UTF_8))

    companion object {
        const val ID = "id"
        const val ROLE = "roles"
    }
}