package com.springboot_cache_redis.config;

import com.github.javafaker.Faker;
import com.springboot_cache_redis.domain.UserEntity;
import com.springboot_cache_redis.repositories.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.stream.IntStream;

@Slf4j
@Component
@RequiredArgsConstructor
public class DatabaseSeeder {

    private final UserJpaRepository userRepository;
    private final SeedProperties seedProperties;

    @Transactional
    @EventListener(ApplicationReadyEvent.class)
    public void seed() {
        var total = userRepository.count();
        log.info("Total de usuários encontrados: {}", total);

        if (total == 0) {
            var userCount = seedProperties.getUserCount();
            var faker = new Faker();

            var users = IntStream.range(0, userCount)
                    .mapToObj(i -> new UserEntity(
                            UUID.randomUUID(),
                            faker.name().fullName(),
                            faker.internet().emailAddress()))
                    .toList();

            userRepository.saveAll(users);
            log.info("Inseridos {} usuários fake no banco de dados.", userCount);
        } else {
            log.info("Banco já possui usuários. Nenhum dado foi inserido.");
        }
    }
}