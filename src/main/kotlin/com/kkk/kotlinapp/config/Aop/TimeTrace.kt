package com.kkk.kotlinapp.config.Aop

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component



@Aspect
@Component
class TimeTrace {

    private val log = LoggerFactory.getLogger(this.javaClass)!!

    @Around("execution(* com.kkk.kotlinapp..*(..))")
    @Throws(Throwable::class)
    fun execute(joinPoint: ProceedingJoinPoint) : Any?{
        val start: Long = System.currentTimeMillis()
        log.info("START: $joinPoint")
        println("==========================================")
        return try{
            joinPoint.proceed()
        } finally {
            val finish = System.currentTimeMillis()
            val timeMs = finish - start
            log.info("END: $joinPoint $timeMs ms")
            println("==========================================")
        }
    }
}