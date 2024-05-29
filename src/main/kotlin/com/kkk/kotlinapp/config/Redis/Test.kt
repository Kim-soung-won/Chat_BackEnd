package com.kkk.kotlinapp.config.Redis

import lombok.Data
import java.io.Serializable
import java.time.LocalDateTime

@Data
data class Test (
    var id: String = "",
    var name: String = "",
    var createdAt: LocalDateTime = LocalDateTime.now()
) : Serializable