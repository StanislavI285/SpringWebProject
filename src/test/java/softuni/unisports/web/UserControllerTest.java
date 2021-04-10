package softuni.unisports.web;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import softuni.unisports.enums.RoleEnum;
import softuni.unisports.model.entity.UserEntity;
import softuni.unisports.repository.RoleRepository;
import softuni.unisports.repository.UserRepository;

import java.util.Set;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class UserControllerTest {

    private static final String NEWS_CONTROLLER_PREFIX = "/users";
    private String testUsername;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @BeforeEach
    private void setUp() {
        init();
    }

//    @AfterEach
//    public void cleanUp() {
//        userRepository.deleteAll();
//
//    }

    private void init() {

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("username").
                setPassword("Aa1@aaaaaa").
                setRoles(Set.of(roleRepository.findByName(RoleEnum.USER).get())).
                setFirstName("Pesho").
                setLastName("Peshov").
                setEmail("pesho@email.com").
                setImageUrl("http://images.com/peshoPicture.jpeg");

        userRepository.save(userEntity);
        testUsername = userEntity.getUsername();
    }

    @Test
    @WithMockUser(username = "username")
    public void showUserProfileShouldWork() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.get(NEWS_CONTROLLER_PREFIX + "/profile/show/{username}", testUsername))
                .andExpect(status().isOk())
                .andExpect(view().name("user-profile"))
                .andExpect(model().attributeExists("userView"));


    }


}
