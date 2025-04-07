package com.springboot_cache_redis.application.service;

import com.springboot_cache_redis.domain.model.User;
import com.springboot_cache_redis.infrastructure.cache.RedisUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserCacheServiceTest {

    @Mock
    private RedisUserRepository redisUserRepository;

    @InjectMocks
    private UserCacheService userCacheService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void cacheUser() {
        User user = new User(UUID.randomUUID(), "John Doe", "john.doe@example.com");

        doNothing().when(redisUserRepository).save(any(User.class));

        userCacheService.cacheUser(user);

        verify(redisUserRepository, times(1)).save(user);
    }

    @Test
    void getCachedUser() {
        UUID userId = UUID.randomUUID();
        User user = new User(userId, "John Doe", "john.doe@example.com");

        when(redisUserRepository.findById(userId.toString())).thenReturn(Optional.of(user));

        Optional<User> result = userCacheService.getCachedUser(userId);

        assertEquals(Optional.of(user), result);
        verify(redisUserRepository, times(1)).findById(userId.toString());
    }
}