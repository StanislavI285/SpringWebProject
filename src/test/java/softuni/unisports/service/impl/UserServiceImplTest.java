package softuni.unisports.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import softuni.unisports.enums.RoleEnum;
import softuni.unisports.model.entity.RoleEntity;
import softuni.unisports.model.entity.UserEntity;
import softuni.unisports.model.service.UserServiceModel;
import softuni.unisports.model.view.UserListViewModel;
import softuni.unisports.model.view.UserViewModel;
import softuni.unisports.repository.RoleRepository;
import softuni.unisports.repository.UserRepository;
import softuni.unisports.security.UniSportsUserDetailsService;
import softuni.unisports.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    private UserEntity userEntity;

    private UserService serviceToTest;

    @Mock
    UserRepository mockUserRepository;
    @Mock
    RoleRepository mockRoleRepository;
    @Mock
    ModelMapper mockModelMapper;
    @Mock
    UniSportsUserDetailsService mockUserDetailsService;
    PasswordEncoder passwordEncoder;

    public UserServiceImplTest() {
    }


    @BeforeEach
    public void setUp() {
        serviceToTest = new UserServiceImpl(mockUserRepository, mockRoleRepository, mockModelMapper,
                mockUserDetailsService, passwordEncoder);

        userEntity = new UserEntity();
        userEntity.setUsername("newUser").
                setPassword("12345");

        RoleEntity roleUser = new RoleEntity();
        roleUser.setName(RoleEnum.USER);

        userEntity.setRoles(Set.of(roleUser));
    }


    @Test
    public void testUserExists() {



        Mockito.when(mockUserRepository.findByUsername("newUser")).
                thenReturn(Optional.of(userEntity));

        boolean userExists = serviceToTest.userExists("newUser");

        Assertions.assertTrue(userExists);
    }

    @Test
    public void testFindUserByUsername() {

        UserServiceModel userServiceModel = new UserServiceModel();
        userServiceModel.setUsername("newUser");

        Mockito.when(mockUserRepository.findByUsername("newUser")).
                thenReturn(Optional.of(userEntity));
        Mockito.when(mockModelMapper.map(userEntity, UserServiceModel.class))
                .thenReturn(userServiceModel);

        UserServiceModel result = serviceToTest.findUserByUsername("newUser");

        Assertions.assertEquals(userEntity.getUsername(), result.getUsername());
    }

    @Test
    public void testGetAllUsers() {

        UserListViewModel userViewModel = new UserListViewModel();
        userViewModel.setUsername("newUser");

        UserEntity secondUserEntity = new UserEntity();
        secondUserEntity.setUsername("newUser2");
        UserListViewModel userViewModel2 = new UserListViewModel();
        userViewModel2.setUsername("newUser2");

        Mockito.when(mockUserRepository.findAll()).
                thenReturn(List.of(userEntity, secondUserEntity));
        Mockito.when(mockModelMapper.map(userEntity, UserListViewModel.class))
                .thenReturn(userViewModel);
        Mockito.when(mockModelMapper.map(secondUserEntity, UserListViewModel.class))
                .thenReturn(userViewModel2);

        List<UserListViewModel> result = serviceToTest.getAllUsers();

        Assertions.assertEquals(2, result.size());
    }


}
