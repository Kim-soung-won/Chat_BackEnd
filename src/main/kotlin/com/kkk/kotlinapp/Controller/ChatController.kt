package com.kkk.kotlinapp.Controller

import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Controller
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Controller
class ChatController(
    private val simpMessagingTemplate: SimpMessagingTemplate
){
    @MessageMapping("/chat")
    fun sendMessage(@Payload message: ChatRequest): Unit{
        var destination: String = determineDestination(message)
        val formater: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        val response: ChatResponse = ChatResponse(message.name, message.content, LocalTime.now().format(formater))
        println("name : "+message.name+"  content : "+message.content)
        simpMessagingTemplate.convertAndSend(destination, response)
    }
    fun determineDestination(message: ChatRequest): String{
        return "/topic/messages"
    }
}