package com.springboot_cache_redis.domain.event;

import com.springboot_cache_redis.infrastructure.cache.RedisUserRepository;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class UserEventListener {

    private final RedisUserRepository redisUserRepository;

    public UserEventListener(RedisUserRepository redisUserRepository) {
        this.redisUserRepository = redisUserRepository;
    }

    @EventListener
    public void handleUserCreated(UserCreatedEvent event) {
        redisUserRepository.save(event.getUser());
    }
}