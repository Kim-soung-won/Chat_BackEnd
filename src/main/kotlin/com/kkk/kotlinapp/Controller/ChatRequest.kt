package com.kkk.kotlinapp.Controller

import java.io.Serializable

data class ChatRequest (
    var name: String="",
    var content: String ="",
) : Serializable