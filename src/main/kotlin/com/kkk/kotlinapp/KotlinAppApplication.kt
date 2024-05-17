package com.kkk.kotlinapp

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.boot.autoconfigure.SpringBootApplication

private val logger = KotlinLogging.logger {}

@SpringBootApplication
class KotlinAppApplication{

}

fun main(args: Array<String>) {
    var arr: List<Int> = listOf(1,2,3,4,5)
    var k = arr.count{ it > 3 }

    println(k)
    var state = State.SING
    println(state)

    state = State.SLEEP
    println(state.isSleeping())

    state = State.EAT
    print(state.message)
}

enum class State(val message: String) {
    SING("노래를 부릅니다."),
    EAT("밥을 먹습니다."),
    SLEEP("잠을 잡니다.");

    fun isSleeping() = this == State.SLEEP
}