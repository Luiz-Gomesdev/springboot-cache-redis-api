package com.springboot_cache_redis.adapter.controller;

import com.springboot_cache_redis.domain.model.User;
import com.springboot_cache_redis.infrastructure.cache.RedisUserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private final RedisUserRepository redisUserRepository;

    public UserController(RedisUserRepository redisUserRepository) {
        this.redisUserRepository = redisUserRepository;
    }

    @PostMapping
    public ResponseEntity<Void> saveUser(@RequestBody User user) {
        redisUserRepository.save(user);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable UUID id) {
        Optional<User> user = redisUserRepository.findById(id.toString());
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}