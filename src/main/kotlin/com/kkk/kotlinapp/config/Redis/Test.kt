package com.kkk.kotlinapp.config.Redis

import lombok.Data
import java.time.LocalDateTime

@Data
class Test {
    var id: String = "";
    var name: String = "";
    var createdAt: LocalDateTime = LocalDateTime.now()
}