package com.kkk.kotlinapp

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.boot.autoconfigure.SpringBootApplication
import lombok.extern.slf4j.Slf4j
import org.springframework.boot.runApplication

private val logger = KotlinLogging.logger {}

@Slf4j
@SpringBootApplication
class KotlinAppApplication

fun main(args: Array<String>) {
    var a = Book("함께 자라기", 20000).apply {
        name = "[초특가]" + name
        discount()
    }
    a.let {
        println("상품명 : ${it.name}, 가격 : ${it.price}")
    }
}

class Book(var name: String, var price: Int) {
    fun discount() {
        price -= 2000
    }
}