package com.springboot_cache_redis.adapter.controller;

import com.springboot_cache_redis.application.dto.UserDTO;
import com.springboot_cache_redis.application.service.UserComparisonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserComparisonService userComparisonService;

    @GetMapping("/db")
    public List<UserDTO> getUsersFromDatabase() {
        long start = System.currentTimeMillis();
        List<UserDTO> users = userComparisonService.getUsersFromDatabase();
        long end = System.currentTimeMillis();
        log.info("Tempo Banco de Dados: {}ms", end - start);
        return users;
    }

    @GetMapping("/cache")
    public List<UserDTO> getUsersFromRedis() {
        long start = System.currentTimeMillis();
        List<UserDTO> users = userComparisonService.getUsersFromRedisCache();
        long end = System.currentTimeMillis();
        log.info("Tempo Cache Redis: {}ms", end - start);
        return users;
    }
}
