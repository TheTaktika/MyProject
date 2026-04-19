package com.max.MyProject.mappers;

import com.max.MyProject.dto.UserDto;
import com.max.MyProject.dto.UserShortDto;
import com.max.MyProject.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .userName(user.getUserName())
                .articles(user.getArticles())
                .build();
    }
    public User toEntity(UserDto dto) {
        return User.builder()
                .id(dto.getId())
                .userName(dto.getUserName())
                .articles(dto.getArticles())
                .build();
    }
    public UserShortDto toShortDto(User user) {
        return UserShortDto.builder()
                .id(user.getId())
                .userName(user.getUserName())
                .build();
    }
    public User toEntityFromShort(UserShortDto dto) {
        return User.builder()
                .id(dto.getId())
                .userName(dto.getUserName())
                .build();
    }
}
