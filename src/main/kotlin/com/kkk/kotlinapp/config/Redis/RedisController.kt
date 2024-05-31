package com.kkk.kotlinapp.config.Redis

import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
@RequiredArgsConstructor
class RedisController (
    private val redisRepository: RedisRepository
) {


    @PostMapping("/test/save")
    fun save():Unit{
        val test = Test("150","하하안녕", LocalDateTime.now())
        redisRepository.save("1",test)
    }
    @GetMapping("/test/get")
    fun get(): Any?{
        return redisRepository.getByKey("1")
    }
}