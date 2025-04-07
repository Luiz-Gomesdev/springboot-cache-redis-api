package com.springboot_cache_redis.application.service;

import com.springboot_cache_redis.domain.model.User;
import com.springboot_cache_redis.infrastructure.cache.RedisUserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserCacheService {

    private final RedisUserRepository redisUserRepository;

    public UserCacheService(RedisUserRepository redisUserRepository) {
        this.redisUserRepository = redisUserRepository;
    }

    public void cacheUser(User user) {
        redisUserRepository.save(user);
    }

    public Optional<User> getCachedUser(UUID id) {
        return redisUserRepository.findById(id.toString());
    }
}