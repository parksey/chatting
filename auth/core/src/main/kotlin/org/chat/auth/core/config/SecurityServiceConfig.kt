package org.chat.auth.core.config

import org.chat.auth.core.filter.AuthenticateFilter
import org.chat.auth.core.service.AuthenticationService
import org.chat.auth.core.service.AuthorizationService
import org.chat.common.jwt.service.JwtAuthenticator
import org.chat.common.jwt.service.JwtProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SecurityServiceConfig {

    @Bean
    fun authenticationFilter(authenticationService: AuthenticationService): AuthenticateFilter {
        return AuthenticateFilter(
            authenticationService = authenticationService
        )
    }

    @Bean
    fun authenticationService(jwtAuthenticator: JwtAuthenticator): AuthenticationService {
        return AuthenticationService(
            jwtAuthenticator = jwtAuthenticator
        )
    }

    @Bean
    fun authorizationService(jwtProvider: JwtProvider): AuthorizationService {
        return AuthorizationService(
            jwtProvider = jwtProvider
        )
    }
}