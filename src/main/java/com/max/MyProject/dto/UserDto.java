package com.max.MyProject.dto;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @NonNull
    private long id;

    @NonNull
    private String userName;

    @NonNull
    private List<ArticleDto> articles;
}