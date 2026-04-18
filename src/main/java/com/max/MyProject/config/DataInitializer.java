package com.max.MyProject.config;

import com.max.MyProject.entities.Article;
import com.max.MyProject.entities.Category;
import com.max.MyProject.entities.User;
import com.max.MyProject.repositories.ArticleRepository;
import com.max.MyProject.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            User admin = User.builder()
                    .userName("admin")
                    .password("12345")
                    .build();
            userRepository.save(admin);

            Article art1 = Article.builder()
                    .title("Моя первая статья")
                    .description("бла-бла-бла")
                    .author(admin)
                    .category(Category.DEVELOPMENT)
                    .build();

            Article art2 = Article.builder()
                    .title("Моя вторая статья")
                    .description("бла-бла-бла")
                    .author(admin)
                    .category(Category.HARDWARE)
                    .build();

            Article art3 = Article.builder()
                    .title("Моя третья статья")
                    .description("бла-бла-бла")
                    .author(admin)
                    .category(Category.DESIGN)
                    .build();

            articleRepository.save(art1);
            articleRepository.save(art2);
            articleRepository.save(art3);
        }
    }
}
