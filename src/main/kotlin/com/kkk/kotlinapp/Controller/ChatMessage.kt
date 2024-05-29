package com.kkk.kotlinapp.Controller

import java.io.Serializable

data class ChatMessage (
    var name: String="",
    var content: String =""
) : Serializable