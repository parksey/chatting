package org.chat.common.jwt.service

import io.jsonwebtoken.JwtBuilder
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.chat.common.config.KeyProperty
import org.chat.common.jwt.vo.GrantType
import org.chat.common.jwt.vo.PublicClaim
import org.chat.common.jwt.vo.Token
import org.springframework.stereotype.Service
import java.util.*

@Service
class JwtProvider(
    private val keyProperty: KeyProperty
) {

    fun create(publicClaim: PublicClaim): Token {
        val accessTokenExpire = calculateExpirationDate(keyProperty.accessExpire)
        val refreshTokenExpire = calculateExpirationDate(keyProperty.refreshExpire)

        val accessToken = provideAccessToken(publicClaim, accessTokenExpire)
        val refreshToken = provideRefreshToken(refreshTokenExpire)

        return Token(
            grantType = GrantType.BEARER,
            accessToken = accessToken,
            accessTokenExpire = accessTokenExpire,
            refreshToken = refreshToken,
            refreshTokenExpire = refreshTokenExpire,
        )
    }

    private fun calculateExpirationDate(expireMillis: Long): Date =
        Date(System.currentTimeMillis() + expireMillis)

    private fun provideAccessToken(publicClaim: PublicClaim, expireTime: Date): String {
        return commonInfo(expireTime)
            .claim(KeyProperty.ID, publicClaim.id)
            .claim(KeyProperty.ROLE, publicClaim.permission)
            .compact();
    }

    private fun provideRefreshToken(expireTime: Date): String {
        return commonInfo(expireTime).compact()
    }

    private fun commonInfo(expireTime: Date): JwtBuilder {
        return Jwts.builder()
            .setHeaderParam("alg", "HS256")
            .setHeaderParam("typ", "JWT")
            .setIssuer(keyProperty.iss)
            .setIssuedAt(Date())
            .setExpiration(expireTime)
            .signWith(keyProperty.secretKey, SignatureAlgorithm.HS512)
    }
}