package com.kkk.kotlinapp.config

import io.github.oshai.kotlinlogging.KLogger
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class LoggerConfig{

    @Value("\${logger.name:com.kkk.kotlinapp}")
    private lateinit var loggerName: String

    @Bean
    fun logger(): KLogger {
        return KotlinLogging.logger(loggerName)
    }
}