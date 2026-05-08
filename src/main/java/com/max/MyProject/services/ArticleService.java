package com.max.MyProject.services;

import com.max.MyProject.dto.ArticleDto;
import com.max.MyProject.entities.Article;
import com.max.MyProject.entities.Category;
import com.max.MyProject.entities.User;
import com.max.MyProject.exceptions.ResourceNotFoundException;
import com.max.MyProject.mappers.ArticleMapper;
import com.max.MyProject.repositories.ArticleRepository;
import com.max.MyProject.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneOffset;
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
                .orElseThrow(() -> new ResourceNotFoundException("User " + username + " not found"));
        Article article = articleMapper.toEntity(dto);
        article.setAuthor(author);
        Article savedArticle = articleRepository.save(article);
        return articleMapper.toDto(savedArticle);
    }
    @Transactional
    public ArticleDto updateArticle(long id, ArticleDto dto, String username) {
        Article article = articleRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Article " + dto.getTitle() + " not found"));
        if (!article.getAuthor().getUsername().equals(username)){
            throw new org.springframework.security.access.AccessDeniedException("You are not the author");
        }
        article.setTitle(dto.getTitle());
        article.setDescription(dto.getDescription());
        article.setCategory(dto.getCategory());
        Article updatedArticle = articleRepository.save(article);
        return articleMapper.toDto(updatedArticle);
    }
    public ArticleDto findArticle (long id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Article not found"));
        return articleMapper.toDto(article);
    }
    @Transactional
    public void deleteArticle (long id, String username) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Article not found"));
        if (!article.getAuthor().getUsername().equals(username)){
            throw new org.springframework.security.access.AccessDeniedException("You are not the author");
        }
        articleRepository.delete(article);
    }
    public List<ArticleDto> searchArticles (String query) {
        return articleRepository.findByTitleContainingIgnoreCase(query)
                .stream()
                .map(articleMapper::toDto)
                .collect(Collectors.toList());
    }
    public List<ArticleDto> getFilteredArticles (String author, String dateBefore,
                                                 String dateAfter, String category) {
        Specification<Article> spec = (root, query, cb) -> cb.conjunction();

        if (author != null && !author.isBlank()) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(root.get("author").get("username"),author));
        }

        if (dateAfter != null && !dateAfter.isBlank()) {
            Instant start = LocalDate.parse(dateAfter).atStartOfDay(ZoneOffset.UTC).toInstant();
            spec = spec.and((root, query, cb) ->
                    cb.greaterThanOrEqualTo(root.get("createdAt"), start));
        }
        if (dateBefore != null && !dateBefore.isBlank()) {
            Instant end = LocalDate.parse(dateBefore).atTime(LocalTime.MAX).atZone(ZoneOffset.UTC).toInstant();
            spec = spec.and((root, query, cb) ->
                    cb.lessThanOrEqualTo(root.get("createdAt"), end));
        }

        if (category != null && !category.isBlank()) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(root.get("category"), Category.valueOf(category)));
        }

        return articleRepository.findAll(spec)
                .stream()
                .map(articleMapper::toDto)
                .collect(Collectors.toList());
    }
}
