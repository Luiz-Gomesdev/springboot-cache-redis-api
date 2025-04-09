package com.springboot_cache_redis.infrastructure.cache;

import com.springboot_cache_redis.application.dto.UserDTO;
import com.springboot_cache_redis.repositories.RedisUserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class UserCacheAdapter {

    private final RedisUserRepository redisUserRepository;

    public UserCacheAdapter(RedisUserRepository redisUserRepository) {
        this.redisUserRepository = redisUserRepository;
    }

    public void save(UserDTO userDTO) {
        redisUserRepository.save(userDTO);
    }

    public Optional<UserDTO> findById(UUID id) {
        return redisUserRepository.findById(id.toString());
    }
}
