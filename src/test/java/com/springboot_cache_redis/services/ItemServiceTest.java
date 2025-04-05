package com.springboot_cache_redis.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@EnableCaching
@ActiveProfiles("test")
public class ItemServiceTest {

    @Autowired
    private ItemService itemService;

    @Autowired
    private CacheManager cacheManager;

    @BeforeEach
    public void setup() {
        cacheManager.getCache("items").clear();
    }

    @Test
    public void testCacheable() {
        String itemId = "123";

        // Primeira chamada, deve demorar
        long startTime = System.currentTimeMillis();
        String item1 = itemService.getItemById(itemId);
        long duration1 = System.currentTimeMillis() - startTime;

        // Segunda chamada, deve ser rápida devido ao cache
        startTime = System.currentTimeMillis();
        String item2 = itemService.getItemById(itemId);
        long duration2 = System.currentTimeMillis() - startTime;

        assertEquals(item1, item2);
        assert duration2 < duration1;
    }
}