package org.chat.auth.core.service

import jakarta.servlet.http.HttpServletRequest
import org.chat.common.jwt.service.JwtAuthenticator
import org.chat.common.jwt.vo.GrantType
import org.chat.common.jwt.vo.PublicClaim
import org.springframework.http.HttpHeaders

class AuthenticationService(
    private val jwtAuthenticator: JwtAuthenticator
) {

    fun extractTokenFromRequest(request: HttpServletRequest): String? {
        return request.getHeader(HttpHeaders.AUTHORIZATION)
            .takeIf { !it.isNullOrEmpty() && it.startsWith(GrantType.BEARER.typeWithSpace) }
            ?.let { refineToken(it) }
            ?.takeIf { jwtAuthenticator.isNotTokenExpire(it) }

    }

    fun getPublicClaim(token: String): PublicClaim {
        return jwtAuthenticator.parseToken(token)
    }

    private fun refineToken(token: String): String {
        return token.trim { it <= ' ' }.replace(GrantType.BEARER.typeWithSpace, "")
    }
}