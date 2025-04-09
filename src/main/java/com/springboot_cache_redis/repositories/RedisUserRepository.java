package com.springboot_cache_redis.repositories;

import com.springboot_cache_redis.application.dto.UserDTO;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class RedisUserRepository {

    private static final String PREFIX = "user:";
    private final RedisTemplate<String, UserDTO> redisTemplate;

    public RedisUserRepository(RedisTemplate<String, UserDTO> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void save(UserDTO userDTO) {
        redisTemplate.opsForValue().set(PREFIX + userDTO.getId(), userDTO);
    }

    public Optional<UserDTO> findById(String id) {
        UserDTO user = redisTemplate.opsForValue().get(PREFIX + id);
        return Optional.ofNullable(user);
    }

    public void delete(UUID id) {
        redisTemplate.delete(PREFIX + id);
    }
}