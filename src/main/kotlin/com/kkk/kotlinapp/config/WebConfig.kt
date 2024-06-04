package com.kkk.kotlinapp.config

import org.springframework.context.annotation.Bean

import org.springframework.context.annotation.Configuration

import org.springframework.web.servlet.config.annotation.CorsRegistry

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer



@Configuration
class WebConfig : WebMvcConfigurer {
    //CORS 무시 설정
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedOrigins("*") // 모든 도메인 허용
            .allowedMethods("*") // 모든 HTTP 메서드 허용
            .allowedHeaders("*") // 모든 헤더 허용
    }
}