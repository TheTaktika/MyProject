package com.max.MyProject.repositories;

import com.max.MyProject.entities.Article;
import com.max.MyProject.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findByCategory(Category category);
    List<Article> findByAuthorId(Long authorId);
    List<Article> findByTitleContainingIgnoreCase(String title);
}
