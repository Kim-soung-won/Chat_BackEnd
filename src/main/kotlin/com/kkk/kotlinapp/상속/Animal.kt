package com.kkk.kotlinapp.상속

import io.github.oshai.kotlinlogging.KotlinLogging

private var logger = KotlinLogging.logger {}

abstract class Animal(
    var name: String,
    var age: Int,
    var type: String
) {
    abstract fun introduce()
    fun eat() {
        println("냠냠")
    }

    init {
        logger.info { "저는 ${type} ${name}이고 나이는 ${age}입니다." }
    }
}