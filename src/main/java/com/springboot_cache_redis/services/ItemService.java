package com.springboot_cache_redis.services;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    @Cacheable("items")
    public String getItemById(String itemId) {
        // Simula uma operação demorada
        try {
            Thread.sleep(3000);
            System.out.println("Fetching item from database...");
        } catch (InterruptedException e) {
            System.err.println("Error while fetching item: " + e.getMessage());
            e.printStackTrace();
        }
        return "Item " + itemId;
    }
}
