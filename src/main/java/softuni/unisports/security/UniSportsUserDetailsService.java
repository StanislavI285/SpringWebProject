package softuni.unisports.security;


import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import softuni.unisports.model.entity.RoleEntity;
import softuni.unisports.model.entity.UserEntity;
import softuni.unisports.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UniSportsUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public UniSportsUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("User with username " + username + " was not found!"));

        return mapToUserDetails(userEntity);
    }

    private UserDetails mapToUserDetails(UserEntity userEntity) {
        List<RoleEntity> roles = userEntity.getRoles();
        List<SimpleGrantedAuthority> authorities = userEntity.
                getRoles().
                stream().
                map(ur -> new SimpleGrantedAuthority("ROLE_" + ur.getName().name())).
                collect(Collectors.toList());

        return new User(
                userEntity.getUsername(),
                userEntity.getPassword(),
                authorities
        );
    }
}
