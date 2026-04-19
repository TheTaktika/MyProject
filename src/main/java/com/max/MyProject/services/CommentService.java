package com.max.MyProject.services;

import com.max.MyProject.dto.CommentDto;
import com.max.MyProject.entities.Article;
import com.max.MyProject.entities.Comment;
import com.max.MyProject.mappers.CommentMapper;
import com.max.MyProject.repositories.ArticleRepository;
import com.max.MyProject.repositories.CommentRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;
    private final CommentMapper commentMapper;

    @Transactional
    public void saveComment(CommentDto dto) {
        Article article = articleRepository.findById(dto.getArticleId())
                .orElseThrow(()-> new EntityNotFoundException("Статья не найдена"));

        Comment comment = commentMapper.toEntity(dto, article);

        commentRepository.save(comment);
    }
}
