package com.youtube.tracker.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import java.util.concurrent.Executor

@Configuration
@EnableAsync
class AsyncConfiguration {
    @Bean(name = ["asyncExecutor"])
    fun asyncExecutor(): Executor {
        return createThreadPoolExecutor()
    }

    private fun createThreadPoolExecutor(): ThreadPoolTaskExecutor {
        val executor = ThreadPoolTaskExecutor()
        executor.corePoolSize = 5
        executor.maxPoolSize = 5
        executor.setQueueCapacity(5)
        executor.keepAliveSeconds = 30
        executor.setWaitForTasksToCompleteOnShutdown(true)
        executor.setThreadNamePrefix("AYSNC-")
        executor.initialize()
        return executor
    }
}