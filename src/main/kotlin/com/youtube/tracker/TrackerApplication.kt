package com.youtube.tracker

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Bean
import java.time.Clock

@SpringBootApplication
@EnableFeignClients
class TrackerApplication {
    @Bean
    fun globalClock() = Clock.systemUTC()
}

fun main(args: Array<String>) {
    runApplication<TrackerApplication>(*args)
}
