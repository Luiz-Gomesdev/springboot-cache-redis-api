package com.springboot_cache_redis.adapter.controller;

import com.springboot_cache_redis.domain.model.User;
import com.springboot_cache_redis.infrastructure.cache.RedisUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private RedisUserRepository redisUserRepository;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveUser() {
        User user = new User(UUID.randomUUID(), "John Doe", "john.doe@example.com");

        doNothing().when(redisUserRepository).save(any(User.class));

        ResponseEntity<Void> response = userController.saveUser(user);

        assertEquals(200, response.getStatusCodeValue());
        verify(redisUserRepository, times(1)).save(user);
    }

    @Test
    void getUserById() {
        UUID userId = UUID.randomUUID();
        User user = new User(userId, "John Doe", "john.doe@example.com");

        when(redisUserRepository.findById(userId.toString())).thenReturn(Optional.of(user));

        ResponseEntity<User> response = userController.getUserById(userId);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(user, response.getBody());
        verify(redisUserRepository, times(1)).findById(userId.toString());
    }
}