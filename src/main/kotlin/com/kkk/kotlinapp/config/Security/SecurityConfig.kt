package com.kkk.kotlinapp.config.Security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration

@Configuration
@EnableWebSecurity
class SecurityConfig(
    val jwtUtil: JWTUtil
) {
    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain{
        http
            .csrf{ it.disable() }
            .cors { cors ->
                cors.configurationSource { request ->
                    val configuration = CorsConfiguration().apply {
                        allowedOriginPatterns = listOf("*")
                        allowedMethods = listOf("*")
                        allowedHeaders = listOf("*")
                        allowCredentials = true
                        maxAge = 3600L

                        exposedHeaders = listOf("Authorization")
                    }
                    configuration
                }
            }
            .formLogin{ it.disable() }
            .httpBasic{ it.disable() }

        http
            .addFilterBefore(JwtRequestFilter(), UsernamePasswordAuthenticationFilter::class.java)


        //        // 세션 매니저 설정 - STATELESS (JWT 사용을 위한 무상태 설정)
        http
            .sessionManagement { session: SessionManagementConfigurer<HttpSecurity?> ->
                session
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
        return http.build()
    }
}