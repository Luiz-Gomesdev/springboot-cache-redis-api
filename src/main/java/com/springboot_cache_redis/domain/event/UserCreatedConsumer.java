package com.springboot_cache_redis.domain.event;

import com.springboot_cache_redis.application.dto.UserDTO;
import com.springboot_cache_redis.infrastructure.cache.UserCacheAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserCreatedConsumer {

    private final UserCacheAdapter cacheAdapter;

    @KafkaListener(topics = "user-created", groupId = "user-group", containerFactory = "kafkaListenerContainerFactory")
    public void consume(UserCreatedMessage message) {
        var userDTO = new UserDTO(
                message.getId(),
                message.getName(),
                message.getEmail()
        );

        cacheAdapter.save(userDTO);
        log.info("📥 Usuário salvo no cache via Kafka: {}", userDTO.getName());
    }
}