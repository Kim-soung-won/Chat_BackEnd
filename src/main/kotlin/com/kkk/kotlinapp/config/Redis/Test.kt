package com.kkk.kotlinapp.config.Redis


data class Test (
    var id: String = "",
    var name: String = "",
    var createdAt: LocalDateTime = LocalDateTime.now()
) : Serializable