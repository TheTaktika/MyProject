package com.max.MyProject.mappers;

import com.max.MyProject.dto.ArticleDto;
import com.max.MyProject.entities.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ArticleMapper {

    private final UserMapper userMapper;


    public ArticleDto toDto(Article article) {

        return ArticleDto.builder()
                .id(article.getId())
                .title(article.getTitle())
                .description(article.getDescription())
                .author(userMapper.toShortDto(article.getAuthor()))
                .createdAt(article.getCreatedAt())
                .category(article.getCategory())
                .build();
    }
    public Article toEntity(ArticleDto dto) {
        return Article.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .author(userMapper.toEntityFromShort(dto.getAuthor()))
                .createdAt(dto.getCreatedAt())
                .category(dto.getCategory())
                .build();
    }
}
