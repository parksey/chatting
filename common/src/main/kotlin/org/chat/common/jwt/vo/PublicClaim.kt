package org.chat.common.jwt.vo

import io.jsonwebtoken.Claims
import org.chat.common.config.KeyProperty

data class PublicClaim(
    val id: Long,
    val permission: String = "USER",
) {
    companion object {
        fun of(claim: Claims): PublicClaim {
            return PublicClaim(
                id = (claim[KeyProperty.ID] as Number).toLong(),
                permission = claim[KeyProperty.ROLE] as String
            )
        }
    }
}