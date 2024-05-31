package com.kkk.kotlinapp.Controller.DTO

import java.io.Serializable

data class ChatResponse (
    var name: String="",
    var content: String ="",
    var timeStamp: String = ""
) : Serializable