package com.max.MyProject.services;

import com.max.MyProject.dto.CommentDto;
import com.max.MyProject.entities.Article;
import com.max.MyProject.entities.Comment;
import com.max.MyProject.entities.User;
import com.max.MyProject.mappers.CommentMapper;
import com.max.MyProject.repositories.ArticleRepository;
import com.max.MyProject.repositories.CommentRepository;
import com.max.MyProject.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;
    private final CommentMapper commentMapper;
    private final UserRepository userRepository;

    @Transactional
    public void saveComment(long articleId, CommentDto dto, String username) {
        User author = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Article article = articleRepository.findById(articleId)
                .orElseThrow(()-> new EntityNotFoundException("Article not found"));

        Comment comment = new Comment();
        comment.setText(dto.getText());
        comment.setArticle(article);
        comment.setAuthor(author);
        comment.setCreatedAt(Instant.now());

        commentRepository.save(comment);
    }
    public List<CommentDto> showComment (long id) {
        return commentRepository.findByArticleId(id)
                .stream().map(commentMapper::toDto).toList();
    }
}
