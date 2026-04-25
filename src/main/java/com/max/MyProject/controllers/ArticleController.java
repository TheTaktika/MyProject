package com.max.MyProject.controllers;

import com.max.MyProject.dto.ArticleDto;
import com.max.MyProject.dto.CommentDto;
import com.max.MyProject.entities.Article;
import com.max.MyProject.entities.Category;
import com.max.MyProject.services.ArticleService;
import com.max.MyProject.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;
    private final CommentService commentService;

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
    @GetMapping("/articles/{id}")
    public String showArticlePage(@PathVariable long id, Model model) {
        ArticleDto dto = articleService.findArticle(id);
        model.addAttribute("articleDto", dto);

        List<CommentDto> comments = commentService.showComment(id);
        model.addAttribute("comments", comments);
        model.addAttribute("newComment", new CommentDto());

        model.addAttribute("isEditing", false);
        return "article-page";
    }
    @GetMapping("/articles/{id}/edit")
    public String showEditForm(@PathVariable long id,
                               Model model,
                               Principal principal) {

        ArticleDto article = articleService.findArticle(id);
        if (principal == null) {
            return "redirect:/login";
        }
        if (!article.getAuthor().getUsername().equals(principal.getName())){
            System.out.println("User: " + principal.getName());
            System.out.println("Author: " + article.getAuthor().getUsername());
            return "redirect:/articles/"+id;
        }
        model.addAttribute("articleDto", article);
        model.addAttribute("categories", Category.values());
        model.addAttribute("isEditing", true);
        return "article-page";
    }
    @PostMapping("/articles/{id}/update")
    public String updateArticle(@PathVariable long id,
                                @ModelAttribute("articleDto") ArticleDto dto,
                                Principal principal) {
        articleService.updateArticle(id, dto, principal.getName());
        return "redirect:/articles/"+id;
    }
    @PostMapping("/articles/{id}/comments")
    public String newComment (@PathVariable long id,
                              @ModelAttribute("newComment") CommentDto dto,
                              Principal principal) {
        String username = principal.getName();
        commentService.saveComment(id, dto, username);
        return "redirect:/articles/"+id;
    }
}
