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

    private long id;

    private String title;

    private String description;

    @JsonProperty("author_id")
    private UserShortDto author;

    @JsonProperty("created_at")
    private Instant createdAt;

    private Category category;
}
