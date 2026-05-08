package com.max.MyProject.mappers;

import com.max.MyProject.dto.ArticleDto;
import com.max.MyProject.dto.UserShortDto;
import com.max.MyProject.entities.Article;
import com.max.MyProject.entities.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;

import static com.max.MyProject.entities.Category.DEVELOPMENT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ArticleMapperTest {
    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private ArticleMapper articleMapper;

    @Test
    void shouldMapArticleDto() {
        User author = new User();
        Article article = Article.builder()
                .id(1)
                .title("Тест")
                .description("Описание")
                .author(author)
                .createdAt(Instant.now())
                .category(DEVELOPMENT)
                .build();

        UserShortDto userShortDto =new UserShortDto();
        when(userMapper.toShortDto(author)).thenReturn(userShortDto);

        ArticleDto result = articleMapper.toDto(article);

        assertNotNull(result);
        assertEquals(article.getId(), result.getId());
        assertEquals(article.getTitle(), result.getTitle());
        assertEquals(userShortDto, result.getAuthor());
    }

    @Test
    void shouldMapDtoToEntity() {
        ArticleDto dto = ArticleDto.builder()
                .id(1)
                .title("Заголовок")
                .description("Текст")
                .category(DEVELOPMENT)
                .build();

        Article result = articleMapper.toEntity(dto);

        assertNotNull(result);
        assertEquals(dto.getId(), result.getId());
        assertEquals(dto.getTitle(), result.getTitle());
        assertEquals(dto.getCategory(), result.getCategory());
    }
}
