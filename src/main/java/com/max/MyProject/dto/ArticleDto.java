package com.max.MyProject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.max.MyProject.entities.Category;
import lombok.*;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDto {

    @NonNull
    private long id;

    @NonNull
    private String title;

    @NonNull
    private String description;

    @NonNull
    @JsonProperty("author_id")
    private UserShortDto author;

    @NonNull
    @JsonProperty("created_at")
    private Instant createdAt;

    @NonNull
    private Category category;
}
