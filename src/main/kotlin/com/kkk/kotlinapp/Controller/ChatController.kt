package com.kkk.kotlinapp.Controller

import com.kkk.kotlinapp.Controller.DTO.ChatRequest
import com.kkk.kotlinapp.Controller.DTO.ChatResponse
import com.kkk.kotlinapp.config.Redis.RedisRepository
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Controller
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Controller
class ChatController(
    private val simpMessagingTemplate: SimpMessagingTemplate,
    private val redisRepository: RedisRepository
){
    @MessageMapping("/chat")
    fun sendMessage(@Payload message: ChatRequest): Unit{
        var destination: String = determineDestination(message.id)
        val formater: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm")
        val response: ChatResponse = ChatResponse(message.name, message.content, LocalTime.now().format(formater))
        simpMessagingTemplate.convertAndSend(destination, response)

        redisRepository.saveChat(message.id, response)
    }
    fun determineDestination(id:String): String{
        println("id: $id")
        return "/topic/messages/$id"
    }
}