package com.max.MyProject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto {

    private long id;

    @JsonProperty("author_id")
    private UserShortDto author;

    @JsonProperty("created_at")
    private Instant createdAt;

    private String text;
}
