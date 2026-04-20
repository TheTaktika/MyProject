package com.max.MyProject.controllers;

import com.max.MyProject.dto.ArticleDto;
import com.max.MyProject.entities.Category;
import com.max.MyProject.services.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("articles", articleService.getAllArticles());
        return "home";
    }
    @PostMapping("/articles/create")
    public String createArticle(@ModelAttribute ArticleDto dto, Principal principal) {
        String username = principal.getName();
        articleService.createArticle(dto, username);
        return "redirect:/";
    }
    @GetMapping("/articles/create")
    public String showCreateForm(Model model) {
        model.addAttribute("articleDto", new ArticleDto());
        model.addAttribute("categories", Category.values());
        return "create-article";
    }
}
