package com.kkk.kotlinapp.Chat.Controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Service

@Service
class BadWordService {
    private val bannedWords: Set<String>

    init{ //경로의 json 파일을 읽어온다. 그 내용을 Set<String> 형태로 저장한다.
        val resource = ClassPathResource("banned_words.json")
        val mapper = jacksonObjectMapper()
        val json = mapper.readValue<Map<String, List<String>>>(resource.inputStream)
        bannedWords = json["banned_words"]?.toSet() ?: emptySet()
    }

    fun containsBadWord(text: String): Boolean {
        return bannedWords.any { text.contains(it, ignoreCase = true) }
    }

    fun filterBadWords(text: String): String {
        return if (containsBadWord(text)) {
            var filteredText = text
            for (word in bannedWords) {
                filteredText = filteredText.replace(word, "사랑해", ignoreCase = true)
            }
            filteredText
        } else {
            text
        }
    }
}