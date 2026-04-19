package com.max.MyProject.mappers;

import com.max.MyProject.dto.CommentDto;
import com.max.MyProject.entities.Article;
import com.max.MyProject.entities.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentMapper {

    private final UserMapper userMapper;



    public CommentDto toDto(Comment comment) {
        return CommentDto.builder()
                .id(comment.getId())
                .author(userMapper.toShortDto(comment.getAuthor()))
                .createdAt(comment.getCreatedAt())
                .text(comment.getText())
                .articleId(comment.getArticle().getId())
                .build();
    }
    public Comment toEntity(CommentDto dto, Article article) {
        return Comment.builder()
                .id(dto.getId())
                .author(userMapper.toEntityFromShort(dto.getAuthor()))
                .createdAt(dto.getCreatedAt())
                .text(dto.getText())
                .article(article)
                .build();
    }
}
