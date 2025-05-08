package org.chat.auth.core.config

import org.chat.auth.core.filter.AuthenticateFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfigurationSource

@Configuration
@Import(SecurityServiceConfig::class)
@EnableWebSecurity
class SecurityConfig(
    private val authenticateFilter: AuthenticateFilter
) {

    @Bean
    fun securityFilterChain(http: HttpSecurity, corsConfigurationSource: CorsConfigurationSource): SecurityFilterChain {
        http
            .csrf { csrf -> csrf.disable() }
            .cors { it.configurationSource(corsConfigurationSource) }
            .httpBasic { httpBasic -> httpBasic.disable() }
            .rememberMe { rememberMe -> rememberMe.disable() }
            .sessionManagement { sessionManagement -> sessionManagement.disable() }
            .formLogin { formLogin -> formLogin.disable() }

        http.authorizeHttpRequests { authorize ->
            authorize
                .requestMatchers(
                    "/",
                    "/swagger-ui/**",
                    "/v3/api-docs/**",
                    "/view/**",
                    "/js/**",
                    "/css/**",
                    "/docs/**",
                    "/actuator/**",
                    "/temp/**",
                    "/login/**",
                    "/codes/**",
                ).permitAll()
                .anyRequest().authenticated()
        }

        http.addFilterBefore(authenticateFilter, UsernamePasswordAuthenticationFilter::class.java)

        return http.build()
    }
}