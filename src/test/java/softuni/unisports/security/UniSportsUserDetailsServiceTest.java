package softuni.unisports.security;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import softuni.unisports.enums.RoleEnum;
import softuni.unisports.model.entity.RoleEntity;
import softuni.unisports.model.entity.UserEntity;
import softuni.unisports.repository.UserRepository;


import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
public class UniSportsUserDetailsServiceTest {

    private UniSportsUserDetailsService serviceToTest;

    @Mock
    UserRepository mockUserRepository;

    @BeforeEach
    public void setUp() {

        serviceToTest = new UniSportsUserDetailsService(mockUserRepository);
    }

    @Test
    public void userNotFound() {
        Assertions.assertThrows(
                UsernameNotFoundException.class, () ->
                        serviceToTest.loadUserByUsername("non-existing"));
    }

    @Test
    public void testExistingUser() {

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("newUser").
                setPassword("12345");

        RoleEntity roleUser = new RoleEntity();
        roleUser.setName(RoleEnum.USER);

        userEntity.setRoles(Set.of(roleUser));

        Mockito.when(mockUserRepository.findByUsername("newUser")).
                thenReturn(Optional.of(userEntity));


        UserDetails userDetails = serviceToTest.loadUserByUsername("newUser");

        Assertions.assertEquals(userEntity.getUsername(), userDetails.getUsername());
        Assertions.assertEquals(1, userDetails.getAuthorities().size());

        List<String> authorities = userDetails.
                getAuthorities().
                stream().
                map(GrantedAuthority::getAuthority).
                collect(Collectors.toList());

        Assertions.assertTrue(authorities.contains("ROLE_USER"));

    }

}
