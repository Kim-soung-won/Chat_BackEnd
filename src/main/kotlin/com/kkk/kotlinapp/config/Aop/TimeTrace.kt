package com.kkk.kotlinapp.config.Aop

import io.github.oshai.kotlinlogging.KLogger
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect

@Aspect
class TimeTrace(
    private val logger: KLogger
) {

    @Around("execution(* com.kkk.kotlinapp..*(..))")
    @Throws(Throwable::class)
    fun execute(joinPoint: ProceedingJoinPoint) : Any?{
        val start: Long = System.currentTimeMillis()
        logger.info { "START: $joinPoint" }
        println("==========================================")
        return try{
            joinPoint.proceed()
        } finally {
            val finish = System.currentTimeMillis()
            val timeMs = finish - start
            logger.info { "END: $joinPoint $timeMs ms" }
            println("==========================================")
        }
    }
}