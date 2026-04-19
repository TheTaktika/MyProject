package com.max.MyProject.mappers;

import com.max.MyProject.dto.MessageDto;
import com.max.MyProject.entities.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageMapper {
    private final UserMapper userMapper;

    public MessageDto toDto(Message message) {
        return MessageDto.builder()
                .id(message.getId())
                .author(userMapper.toShortDto(message.getAuthor()))
                .text(message.getText())
                .createdAt(message.getCreatedAt())
                .build();
    }
    public Message toEntity(MessageDto dto) {
        return Message.builder()
                .id(dto.getId())
                .author(userMapper.toEntityFromShort(dto.getAuthor()))
                .text(dto.getText())
                .createdAt(dto.getCreatedAt())
                .build();
    }
}
