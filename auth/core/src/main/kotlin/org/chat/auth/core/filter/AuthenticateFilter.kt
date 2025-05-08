package org.chat.auth.core.filter

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.chat.auth.core.service.AuthenticationService
import org.chat.auth.core.vo.AuthUser
import org.chat.common.jwt.vo.PublicClaim
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter

class AuthenticateFilter(
    private val authenticationService: AuthenticationService
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {

        val token = authenticationService.extractTokenFromRequest(request)

        if (token != null) {
            val authenticatedResult = kotlin.runCatching { authenticationService.getPublicClaim(token) }

            authenticatedResult.onSuccess { publicClaim ->
                SecurityContextHolder.getContext().authentication = getAuthentication(publicClaim)
                filterChain.doFilter(request, response)
            }.onFailure { exception ->
                sendErrorResponse(response, exception as Exception)
            }
        } else {
            filterChain.doFilter(request, response)
        }
    }

    private fun getAuthentication(publicClaim: PublicClaim): Authentication {
        return UsernamePasswordAuthenticationToken(
            AuthUser(publicClaim.id), null,
            listOf(SimpleGrantedAuthority(publicClaim.permission))
        )
    }

    private fun sendErrorResponse(response: HttpServletResponse, exception: Exception) {
        response.status = HttpStatus.UNAUTHORIZED.value()
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.characterEncoding = Charsets.UTF_8.name()
        response.writer.print(exception.message)
    }
}