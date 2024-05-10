package com.kkk.kotlinapp

import io.github.oshai.kotlinlogging.KotlinLogging
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

private val logger = KotlinLogging.logger {}

class testDTO(
    var name: String,
    var age : Int
) {
    init {
        logger.info { "${this.age}살, ${this.name}님이 생성돼셨습니다." }
    }
    constructor() : this("기본 이름", -1){
        logger.info { "${this.age}살, ${this.name}님 생성" }
    }
}