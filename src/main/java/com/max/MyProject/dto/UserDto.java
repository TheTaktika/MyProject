package com.max.MyProject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.max.MyProject.entities.Article;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private long id;

    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private List<Article> articles;
}