package softuni.unisports.web;


import org.apache.tomcat.jni.Local;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import softuni.unisports.enums.CategoryEnum;
import softuni.unisports.model.entity.CategoryEntity;
import softuni.unisports.model.entity.NewsEntity;
import softuni.unisports.repository.CategoryRepository;
import softuni.unisports.repository.NewsRepository;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private CategoryRepository categoryRepository;


    @Test
    @WithMockUser(username = "username", roles = {"ADMIN", "USER"})
    public void shouldReturnValidStatusViewNameAndModel() throws Exception {

        for (CategoryEnum value : CategoryEnum.values()) {
            categoryRepository.save(new CategoryEntity().setName(value));
        }

        for (int i = 0; i < 10; i++) {
            NewsEntity news = new NewsEntity().setViews(1000)
                    .setTitle("Title")
                    .setAddedOn(LocalDateTime.now())
                    .setContent("Content")
                    .setImageUrl("https://www.images.com/someImage.jpg")
                    .setVideoUrl("cscWSibQFOI")
                    .setCategory(categoryRepository.findById(1L).get());
            newsRepository.save(news);
        }




        mockMvc
                .perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("currentDate"))
                .andExpect(model().attributeExists("latestNews"))
                .andExpect(model().attributeExists("mostCommentedNews"))
                .andExpect(model().attributeExists("categories"))
                .andExpect(model().attributeExists("newsWithMoreThan10Views"))
                .andExpect(model().attributeExists("topVideoNews"))
                .andExpect(model().attributeExists("latestVideoNews"));


    }

}
