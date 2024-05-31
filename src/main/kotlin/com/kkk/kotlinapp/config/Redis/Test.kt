package com.kkk.kotlinapp.config.Redis

import java.io.Serializable
import java.time.LocalDateTime

data class Test (
    var id: String = "",
    var name: String = "",
    var createdAt: LocalDateTime = LocalDateTime.now()
) : Serializable