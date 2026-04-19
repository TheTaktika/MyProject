package com.max.MyProject.dto;

import com.max.MyProject.entities.Article;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private long id;

    private String userName;

    private List<Article> articles;
}