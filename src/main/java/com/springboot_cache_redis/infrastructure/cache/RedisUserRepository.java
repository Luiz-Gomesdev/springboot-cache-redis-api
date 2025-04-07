package com.springboot_cache_redis.infrastructure.cache;

import com.springboot_cache_redis.domain.model.User;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RedisUserRepository {

    private final RedisTemplate<String, User> redisTemplate;

    public RedisUserRepository(RedisTemplate<String, User> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void save(User user) {
        redisTemplate.opsForValue().set("user:" + user.id(), user);
    }

    public Optional<User> findById(String id) {
        return Optional.ofNullable(redisTemplate.opsForValue().get("user:" + id));
    }
}