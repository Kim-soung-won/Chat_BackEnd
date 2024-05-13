package com.kkk.kotlinapp

import com.kkk.javaapp.ko
import com.kkk.kotlinapp.상속.Counter
import com.kkk.kotlinapp.상속.Dog
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

private val logger = KotlinLogging.logger {}

@SpringBootApplication
class KotlinAppApplication

fun main(args: Array<String>) {
    EventPrinter().start()
}

interface EventListener {
    fun onEvent(count: Int)
}

class Counter(var listener: EventListener){
    fun count() {
        for(i in 1..100)
            if(i%5 == 0) listener.onEvent(i)
    }
}

class EventPrinter: EventListener {
    override fun onEvent(count:Int){
        print("${count}-")
    }
    fun start(){
        var counter = Counter(this)
        counter.count()
    }
}

