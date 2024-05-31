package com.kkk.kotlinapp.config.Redis

import com.kkk.kotlinapp.Controller.DTO.ChatResponse
import lombok.RequiredArgsConstructor
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository

@Repository
@RequiredArgsConstructor
class RedisRepository(
    private val redisTemplate: RedisTemplate<String, Any>
) {
    val HASH_KEY: String = "Hello"

    fun save(id:String, test: Test): Unit{
        redisTemplate.opsForHash<String, Any>().put(HASH_KEY, id, test)
    }

    fun getByKey(key: String): Any?{
        return redisTemplate.opsForHash<String, Any>().get(HASH_KEY, key)
    }

    fun saveChat(chatRoomId: String, message: ChatResponse){
        val key = getChatRoomKey(chatRoomId)
        println("여기까지 잘 왔어요!!!")
        redisTemplate.opsForList().rightPush(key,message)
    }

    fun getChatRoomKey(chatRoomId:String) = "chatroom:$chatRoomId"

}