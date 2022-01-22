package com.youtube.tracker

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import kotlin.jvm.JvmStatic
import org.springframework.boot.SpringApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableFeignClients
class TrackerApplication

fun main(args: Array<String>) {
    runApplication<TrackerApplication>(*args)
}
