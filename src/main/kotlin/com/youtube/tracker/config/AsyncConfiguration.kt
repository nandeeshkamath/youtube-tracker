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
        executor.corePoolSize = poolSize
        executor.maxPoolSize = maxPoolSize
        executor.setQueueCapacity(queueCapacity)
        executor.keepAliveSeconds = keepAliveSeconds
        executor.setWaitForTasksToCompleteOnShutdown(true)
        executor.setThreadNamePrefix("AYSNC-")
        executor.initialize()
        return executor
    }

    companion object {
        private const val poolSize = 5
        private const val maxPoolSize = 5
        private const val queueCapacity = 5
        private const val keepAliveSeconds = 30
    }
}
