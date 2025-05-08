package org.chat.common.jwt.service

import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import org.chat.common.config.KeyProperty
import org.chat.common.jwt.vo.PublicClaim
import org.springframework.stereotype.Component

@Component
class JwtAuthenticator(
    private val jwtProperty: KeyProperty
) {

    fun isNotTokenExpire(token: String): Boolean {
        try {
            Jwts.parserBuilder()
                .setSigningKey(jwtProperty.secretKey)
                .build()
                .parseClaimsJws(token)
            return true
        } catch (expiredJwtException: ExpiredJwtException) {
            return false
        } catch (exception: Exception) {
            throw IllegalArgumentException("Invalid Token")
        }
    }

    fun parseToken(token: String): PublicClaim {
        try {
            val claim = Jwts.parserBuilder()
                .setSigningKey(jwtProperty.secretKey)
                .build()
                .parseClaimsJws(token)
                .body

            return PublicClaim.of(claim)
        } catch (exception: Exception) {
            throw IllegalArgumentException("Invalid Token")
        }
    }
}