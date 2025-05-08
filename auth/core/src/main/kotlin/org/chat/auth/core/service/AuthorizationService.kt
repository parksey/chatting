package org.chat.auth.core.service

import jakarta.servlet.http.HttpServletResponse
import org.chat.common.jwt.service.JwtProvider
import org.chat.common.jwt.vo.GrantType
import org.chat.common.jwt.vo.PublicClaim
import org.chat.common.jwt.vo.Token
import org.springframework.http.HttpHeaders

class AuthorizationService(
    private val jwtProvider: JwtProvider
) {

    fun provideNewToken(response: HttpServletResponse, publicClaim: PublicClaim) {
        val token: Token = jwtProvider.create(publicClaim);
        response.addHeader(HttpHeaders.AUTHORIZATION, GrantType.BEARER.typeWithSpace + token.accessToken);
    }
}