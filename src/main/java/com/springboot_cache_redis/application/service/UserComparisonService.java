package com.springboot_cache_redis.application.service;

import com.springboot_cache_redis.application.dto.UserDTO;
import com.springboot_cache_redis.application.mapper.UserMapper;
import com.springboot_cache_redis.repositories.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserComparisonService {

    private final UserJpaRepository userRepository;
    private final UserMapper userMapper;

    // Busca sem cache (direto do banco)
    public List<UserDTO> getUsersFromDatabase() {
        var users = userRepository.findAll();
        return userMapper.toDTOList(users);
    }

    // Busca com cache Redis
    @Cacheable(value = "users")
    public List<UserDTO> getUsersFromRedisCache() {
        var users = userRepository.findAll();
        return userMapper.toDTOList(users);
    }
}