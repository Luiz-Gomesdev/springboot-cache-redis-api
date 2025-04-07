package com.springboot_cache_redis.domain.model;

import java.io.Serializable;
import java.util.UUID;

public record User(
        UUID id,
        String name,
        String email
) implements Serializable { }
