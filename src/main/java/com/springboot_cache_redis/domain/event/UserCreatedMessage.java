package com.springboot_cache_redis.domain.event;

import java.util.UUID;

public class UserCreatedMessage {
    private UUID id;
    private String name;
    private String email;

    public UserCreatedMessage() {}

    public UserCreatedMessage(UUID id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}