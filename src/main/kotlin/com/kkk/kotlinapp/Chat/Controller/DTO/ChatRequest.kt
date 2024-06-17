package com.kkk.kotlinapp.Chat.Controller.DTO

import java.io.Serializable

data class ChatRequest (
    var id: String="", //채팅방 링크
    var name: String="", // 보낸 사람
    var content: String ="", // 채팅 내용
) : Serializable