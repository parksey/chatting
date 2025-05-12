package org.chat.polling.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
class CorsConfig {

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration()

        configuration.allowedOrigins = listOf(
            "http://localhost:5174",
        )

        configuration.allowedMethods = listOf("HEAD", "OPTIONS", "GET", "POST", "PUT", "PATCH", "DELETE")
        configuration.allowedHeaders = listOf("Authorization", "Content-Type", "language")
        configuration.exposedHeaders = listOf("Authorization", "Cache-Control", "ETag", "Vary")
        configuration.allowCredentials = true
        configuration.maxAge = 3600L

        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)

        return source
    }
}