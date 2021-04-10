package softuni.unisports.web.api;


import com.google.gson.Gson;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import softuni.unisports.enums.CategoryEnum;
import softuni.unisports.enums.RoleEnum;
import softuni.unisports.model.binding.CommentBindingModel;
import softuni.unisports.model.entity.CategoryEntity;
import softuni.unisports.model.entity.NewsEntity;
import softuni.unisports.model.entity.RoleEntity;
import softuni.unisports.model.entity.UserEntity;
import softuni.unisports.repository.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class CommentsRestControllerTest {

    private String testNewsId;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {
        newsRepository.deleteAll();
        userRepository.deleteAll();
        roleRepository.deleteAll();
        this.init();
    }

    private void init() {
        RoleEntity roleAdmin = new RoleEntity();
        roleAdmin.setName(RoleEnum.ADMIN);
        RoleEntity roleUser = new RoleEntity();
        roleAdmin.setName(RoleEnum.USER);

        roleRepository.saveAll(List.of(roleUser, roleAdmin));

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("username").
                setPassword("Aa1@aaaaaa").
                setRoles(Set.of(roleUser, roleAdmin)).
                setFirstName("Pesho").
                setLastName("Peshov").
                setEmail("pesho@email.com").
                setImageUrl("http://images.com/peshoPicture.jpeg");


        userRepository.save(userEntity);


        CategoryEntity categoryEntity = new CategoryEntity(CategoryEnum.FOOTBALL);
        categoryRepository.save(categoryEntity);

        NewsEntity newsEntity = new NewsEntity();
        newsEntity.setTitle("title").
                setAuthor(userEntity).
                setContent("some interesting stuff happened yesterday").
                setCategory(categoryEntity).
                setAddedOn(LocalDateTime.now()).
                setImageUrl("https://newspictures.com/news.jpeg").
                setComments(new HashSet<>());

        newsRepository.save(newsEntity);
        testNewsId = newsEntity.getId();

    }

    @Test
    @WithMockUser(value = "username", roles = "USER")
    public void testAddComment() throws Exception {

        CommentBindingModel comment = new CommentBindingModel();
        comment.
                setAuthor("username").
                setContent("Comment content").
                setNewsId(testNewsId);
        String json = new Gson().toJson(comment);


        mockMvc.perform(
                MockMvcRequestBuilders.post("/comments/add").
                        accept(MediaType.APPLICATION_JSON).
                        contentType(MediaType.APPLICATION_JSON).
                        content(json).
                        with(csrf())).
                andExpect(status().isCreated());
    }


}
