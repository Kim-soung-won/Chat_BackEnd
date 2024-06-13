package com.kkk.kotlinapp

import io.github.oshai.kotlinlogging.KotlinLogging
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication
class KotlinAppApplication

fun main(args: Array<String>) {
    runApplication<KotlinAppApplication>(*args)
}