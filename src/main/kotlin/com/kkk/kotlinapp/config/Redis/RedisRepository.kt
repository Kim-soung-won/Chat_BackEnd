package com.kkk.kotlinapp.config.Redis

import lombok.RequiredArgsConstructor
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository

@Repository
@RequiredArgsConstructor
class RedisRepository(
    private val redisTemplate: RedisTemplate<String, Any>
) {
    val HASH_KEY: String = "TEST"

    fun save(id:String, test: Test): Unit{
        redisTemplate.opsForHash<String, Any>().put(HASH_KEY, id, test)
    }
    fun getByKey(key: String): Any?{
        return redisTemplate.opsForHash<String, Any>().get(HASH_KEY, key)
    }
}