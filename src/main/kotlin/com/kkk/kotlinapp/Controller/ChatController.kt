package com.kkk.kotlinapp.Controller

import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Controller

@Controller
class ChatController(
    private val simpMessagingTemplate: SimpMessagingTemplate
){
    @MessageMapping("/chat")
    fun sendMessage(@Payload message: ChatMessage): Unit{
        var destination: String = determineDestination(message)
        simpMessagingTemplate.convertAndSend(destination, message)
    }
    fun determineDestination(message: ChatMessage): String{
        return "/topic/messages"
    }
}