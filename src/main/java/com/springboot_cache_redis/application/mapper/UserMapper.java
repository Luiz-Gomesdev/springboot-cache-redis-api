package com.springboot_cache_redis.application.mapper;

import com.springboot_cache_redis.application.dto.UserDTO;
import com.springboot_cache_redis.domain.UserEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public UserDTO toDTO(UserEntity entity) {
        if (entity == null) return null;
        return new UserDTO(entity.getId(), entity.getName(), entity.getEmail());
    }

    public UserEntity toEntity(UserDTO dto) {
        if (dto == null) return null;
        return new UserEntity(dto.getId(), dto.getName(), dto.getEmail());
    }

    public List<UserDTO> toDTOList(List<UserEntity> entities) {
        return entities.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
