package com.springboot_cache_redis.adapter.controller;

import com.springboot_cache_redis.application.dto.UserDTO;
import com.springboot_cache_redis.application.service.UserComparisonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
@Import(UserControllerTest.MockConfig.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserComparisonService userComparisonService;

    @TestConfiguration
    static class MockConfig {
        @Bean
        public UserComparisonService userComparisonService() {
            return mock(UserComparisonService.class);
        }
    }

    @Test
    void shouldReturnUsersFromDatabase() throws Exception {
        List<UserDTO> users = List.of(
                new UserDTO(UUID.randomUUID(), "Luiz", "luiz@example.com")
        );
        when(userComparisonService.getUsersFromDatabase()).thenReturn(users);

        mockMvc.perform(get("/users/db"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].name").value("Luiz"));
    }

    @Test
    void shouldReturnUsersFromRedisCache() throws Exception {
        List<UserDTO> users = List.of(
                new UserDTO(UUID.randomUUID(), "Joana", "joana@example.com")
        );
        when(userComparisonService.getUsersFromRedisCache()).thenReturn(users);

        mockMvc.perform(get("/users/cache"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].email").value("joana@example.com"));
    }
}
