package com.max.MyProject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {

    @NonNull
    private long id;

    @NonNull
    @JsonProperty("author_id")
    private UserShortDto author;

    @NonNull
    @JsonProperty("created_at")
    private Instant createdAt;

    @NonNull
    private String text;

    @NonNull
    private long articleId;
}
