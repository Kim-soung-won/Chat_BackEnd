package com.kkk.kotlinapp.Chat.Controller

import com.kkk.kotlinapp.Chat.Controller.DTO.ChatRequest
import com.kkk.kotlinapp.Chat.Controller.DTO.ChatResponse
import com.kkk.kotlinapp.Chat.Service.ChatService
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import java.time.LocalDateTime
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

        var response = ChatResponse(message.name, message.content, LocalDateTime.now().format(formater))
        simpMessagingTemplate.convertAndSend(destination, response)

        chatService.countChat(message.id, message.name) // 상대방이 안읽은 채팅 갯수 추가
        chatService.saveChat(message.id, response) //redis에 쓰기
    }

    fun determineDestination(id:String): String{ //채팅 구독 경로 설정(URL을 기준으로)
        return "/topic/messages/$id"
    }

    @GetMapping("/chat/history") //채팅 내역 조회
    @ResponseBody
    fun getChatHistory(@RequestParam id: String, @RequestParam user: String): List<ChatResponse>{
        println("============$user===================")
        chatService.readChat(id, user) // 안읽은 채팅 count 삭제
        return chatService.getMessageHistory(id)
    }

//    @PostMapping("/api/chat/redirect")
//    @ResponseBody
//    fun redirectToChat(authentication: Authentication) : ResponseEntity<Unit> {
//        val username: String = authentication.username
//        println("username : $username")
//
//        return ResponseEntity.status(HttpStatus.FOUND)
//            .header("Location", "http://localhost:3000/chat")
//            .build()
//    }
}