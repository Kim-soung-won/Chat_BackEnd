package com.kkk.kotlinapp.Chat.Controller.DTO

import java.io.Serializable

data class ChatResponse (
    var name: String="",
    var content: String ="",
    var timeStamp: String = ""
) : Serializable