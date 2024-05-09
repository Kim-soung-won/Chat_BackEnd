package com.kkk.kotlinapp

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import kotlin.math.log


private val logger = KotlinLogging.logger {}

@SpringBootApplication
class KotlinAppApplication
fun main(args: Array<String>) {
	logger.info {"hello"}
	println("Hello Kotlin");
	runApplication<KotlinAppApplication>(*args)
}
