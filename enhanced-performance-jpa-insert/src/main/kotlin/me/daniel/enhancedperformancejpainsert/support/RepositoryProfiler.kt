package me.daniel.enhancedperformancejpainsert.support

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component


@Aspect
@Component
class RepositoryProfiler {
    var logger: Logger = LoggerFactory.getLogger(this.javaClass)

    @Pointcut("execution(public * org.springframework.data.repository.Repository+.*(..))")
    fun intercept() {}

    @Around("intercept()")
    fun profile(joinPoint: ProceedingJoinPoint): Any? {
        val startMs = System.currentTimeMillis()
        var result: Any? = null
        try {
            result = joinPoint.proceed()
        } catch (e: Throwable) {
            logger.error(e.message, e)
            // do whatever you want with the exception
        }
        val elapsedMs = System.currentTimeMillis() - startMs
        // you may like to use logger.debug
        logger.info(joinPoint.target.toString() + "." + joinPoint.signature + ": Execution time: " + elapsedMs + " ms")
        // pay attention that this line may return null
        return result
    }

}