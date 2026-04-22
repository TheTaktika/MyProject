package com.max.MyProject.services;

import com.max.MyProject.dto.ArticleDto;
import com.max.MyProject.entities.Article;
import com.max.MyProject.entities.User;
import com.max.MyProject.mappers.ArticleMapper;
import com.max.MyProject.repositories.ArticleRepository;
import com.max.MyProject.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final ArticleMapper articleMapper;

    public List<ArticleDto> getAllArticles() {
        return articleRepository.findAll().stream()
                .map(articleMapper::toDto)
                .collect(Collectors.toList());
    }
    @Transactional
    public ArticleDto createArticle(ArticleDto dto, String username){
        User author = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));
        Article article = articleMapper.toEntity(dto);
        article.setAuthor(author);
        Article savedArticle = articleRepository.save(article);
        return articleMapper.toDto(savedArticle);
    }
    @Transactional
    public ArticleDto updateArticle(long id, ArticleDto dto) {
        Article article = articleRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Статья не найдена"));
        article.setTitle(dto.getTitle());
        article.setDescription(dto.getDescription());
        article.setCategory(dto.getCategory());
        Article updatedArticle = articleRepository.save(article);
        return articleMapper.toDto(updatedArticle);
    }
    public ArticleDto findArticle (long id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Статья не найдена"));
        return articleMapper.toDto(article);
    }
}
