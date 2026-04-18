package com.max.MyProject.controllers;

import com.max.MyProject.services.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final ArticleService articleService;

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("articles", articleService.getAllArticles());
        return "home";
    }
}
