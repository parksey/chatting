package org.chat.common.jwt.vo

import io.jsonwebtoken.Claims
import org.chat.common.config.KeyProperty

data class PublicClaim(
    val id: Long,
    val permission: String,
) {
    companion object {
        fun of(claim: Claims): PublicClaim {
            return PublicClaim(
                id = claim[KeyProperty.ID] as Long,
                permission = claim[KeyProperty.ROLE] as String
            )
        }
    }
}