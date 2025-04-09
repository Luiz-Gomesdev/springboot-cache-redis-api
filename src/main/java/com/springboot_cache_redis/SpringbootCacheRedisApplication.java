package com.springboot_cache_redis;

import com.springboot_cache_redis.config.SeedProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication(scanBasePackages = "com.springboot_cache_redis")
@EnableCaching
@EnableConfigurationProperties(SeedProperties.class)
public class SpringbootCacheRedisApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootCacheRedisApplication.class, args);
	}
}
