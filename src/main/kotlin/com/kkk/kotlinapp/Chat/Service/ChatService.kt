package com.kkk.kotlinapp.Chat.Service

import com.kkk.kotlinapp.Chat.Controller.DTO.ChatResponse
import jakarta.transaction.Transactional
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service

@Service
class ChatService(
    private val redisTemplate: RedisTemplate<String, Any>
) {
    fun saveChat(chatRoomId: String, message: ChatResponse){
        val key = getChatRoomKey(chatRoomId)
        redisTemplate.opsForList().rightPush(key,message)
    }

    fun getChatRoomKey(chatRoomId:String) = "chatroom:$chatRoomId"

    fun getMessageHistory(chatRoomId: String): List<ChatResponse>{
        val key = getChatRoomKey(chatRoomId)
        return redisTemplate.opsForList().range(key,0,-1)?.map { it as ChatResponse } ?: emptyList()
    }
}