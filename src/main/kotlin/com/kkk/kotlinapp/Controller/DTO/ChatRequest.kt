package com.kkk.kotlinapp.Controller.DTO

import java.io.Serializable

data class ChatRequest (
    var id: String="",
    var name: String="",
    var content: String ="",
) : Serializable