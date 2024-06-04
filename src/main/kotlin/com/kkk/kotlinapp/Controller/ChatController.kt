package com.kkk.kotlinapp.Controller

import com.kkk.kotlinapp.Controller.DTO.ChatRequest
import com.kkk.kotlinapp.Controller.DTO.ChatResponse
import com.kkk.kotlinapp.Service.ChatService
import com.kkk.kotlinapp.config.Redis.RedisRepository
import jakarta.servlet.http.HttpServletResponse
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Controller
class ChatController(
    private val simpMessagingTemplate: SimpMessagingTemplate,
    private val chatService: ChatService,
    private val wordfiltering: BadWordService
){
    @MessageMapping("/chat") //채팅 보내기
    fun sendMessage(@Payload message: ChatRequest): Unit{
        var destination: String = determineDestination(message.id)
        val formater: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm")
        message.content = wordfiltering.filterBadWords(message.content)
        val response: ChatResponse = ChatResponse(message.name, message.content, LocalTime.now().format(formater))
        simpMessagingTemplate.convertAndSend(destination, response)

        chatService.saveChat(message.id, response)
    }
    fun determineDestination(id:String): String{ //채팅 구독 경로 설정(URL을 기준으로)
        return "/topic/messages/$id"
    }

    @GetMapping("/chat/history") //채팅 내역 조회
    @ResponseBody
    fun getChatHistory(@RequestParam id: String): List<ChatResponse>{
        return chatService.getMessageHistory(id)
    }

}