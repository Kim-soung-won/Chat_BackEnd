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

    fun countChat(chatRoomId: String, userName: String){
        // 채팅 보낼때 마다 안읽은 chat 갯수 증가
        // 1씩 증가시키며 값 저장
        val unreadKey = "count:$chatRoomId$userName" //보내는 사람 이름으로 저장
        val unreadCount = redisTemplate.opsForValue().increment(unreadKey, 1)
        if((unreadCount?.compareTo(300) ?: 0) > 0) { // null 처리 compareTo는 비교처리로 같으면 0 작으면 -1
            redisTemplate.opsForValue().set(unreadKey, 300)
        }
    }
    
    fun readChat(chatRoomId: String, userName: String){
        // 채팅을 읽었을 때 안읽은 채팅 갯수 초기화
        redisTemplate.opsForValue().set("count:$chatRoomId$userName", 0)
    }

    fun getChatRoomKey(chatRoomId:String) = "chatroom:$chatRoomId"

    fun getMessageHistory(chatRoomId: String): List<ChatResponse>{
        val key = getChatRoomKey(chatRoomId)
        return redisTemplate.opsForList().range(key,0,-1)?.map { it as ChatResponse } ?: emptyList()
    }
}