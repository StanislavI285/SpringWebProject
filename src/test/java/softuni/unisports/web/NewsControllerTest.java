package softuni.unisports.web;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;
import softuni.unisports.enums.CategoryEnum;
import softuni.unisports.enums.RoleEnum;
import softuni.unisports.model.entity.CategoryEntity;
import softuni.unisports.model.entity.NewsEntity;
import softuni.unisports.model.entity.RoleEntity;
import softuni.unisports.model.entity.UserEntity;
import softuni.unisports.repository.CategoryRepository;
import softuni.unisports.repository.NewsRepository;
import softuni.unisports.repository.RoleRepository;
import softuni.unisports.repository.UserRepository;
import softuni.unisports.service.CloudinaryService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class NewsControllerTest {


    private static final String NEWS_CONTROLLER_PREFIX = "/news";
    private String testNewsId;

    @MockBean
    CloudinaryService mockCloudinaryService;


    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private RoleRepository roleRepository;


    @BeforeEach
    public void setUp() throws IOException {
        when(mockCloudinaryService.uploadImage(Mockito.any())).thenReturn("https://images.com/image.png");
        this.init();
    }

    @AfterEach
    public void cleanUp() {
        newsRepository.deleteAll();
        userRepository.deleteAll();
    }

    private void init() {


        RoleEntity roleAdmin = roleRepository.findByName(RoleEnum.ADMIN).get();
        RoleEntity roleUser = roleRepository.findByName(RoleEnum.USER).get();
        RoleEntity roleModerator = roleRepository.findByName(RoleEnum.MODERATOR).get();

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("username").
                setPassword("Aa1@aaaaaa").
                setRoles(Set.of(roleUser, roleAdmin, roleModerator)).
                setFirstName("Pesho").
                setLastName("Peshov").
                setEmail("pesho@email.com").
                setImageUrl("http://images.com/peshoPicture.jpeg");


        userRepository.save(userEntity);


        CategoryEntity categoryEntity = categoryRepository.findByName(CategoryEnum.FOOTBALL).get();


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
    @WithMockUser(username = "username", roles = {"ADMIN", "USER"})
    public void shouldReturnValidStatusViewNameAndModel() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.get(NEWS_CONTROLLER_PREFIX + "/{id}", testNewsId))
                .andExpect(status().isOk())
                .andExpect(view().name("article"))
                .andExpect(model().attributeExists("newsViewModel"));


    }

    @Test
    @WithMockUser(username = "username", roles = {"ADMIN", "USER", "MODERATOR"})
    public void testAddNewsShouldWork() throws Exception {

        //TODO check - returns status 403

        MockMultipartFile mockImgFile = new MockMultipartFile(
                "image",
                "image.png",
                MediaType.TEXT_PLAIN_VALUE,
                "I am an image" .getBytes()
        );


        mockMvc.perform(
                MockMvcRequestBuilders.multipart(NEWS_CONTROLLER_PREFIX + "/add")
                        .file(mockImgFile)
                        .param("title", "News Titleeeee")
                        .param("content", "News content here")
                        .param("author", "username")
                        .param("category", "FOOTBALL")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection());

        Assertions.assertEquals(2, newsRepository.count());


    }

    @Test
    public void testGetAllNewsShouldWork() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.
                get(NEWS_CONTROLLER_PREFIX + "/all")).
                andExpect(status().isOk()).
                andExpect(view().name("news")).
                andExpect(model().attributeExists("allNews"));
    }


    @Test
    public void testGetNewsShouldThrow() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.
                get(NEWS_CONTROLLER_PREFIX + "invalidnewsid")).
                andExpect(status().isNotFound());
    }

}
