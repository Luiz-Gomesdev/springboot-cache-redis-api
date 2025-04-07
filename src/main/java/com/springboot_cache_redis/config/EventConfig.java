package com.springboot_cache_redis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.SimpleApplicationEventMulticaster;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Configuration
public class EventConfig {

    @Bean(name = "applicationEventMulticaster")
    public SimpleApplicationEventMulticaster applicationEventMulticaster() {
        SimpleApplicationEventMulticaster eventMulticaster = new SimpleApplicationEventMulticaster();
        Executor executor = Executors.newCachedThreadPool();
        eventMulticaster.setTaskExecutor(executor);
        return eventMulticaster;
    }
}