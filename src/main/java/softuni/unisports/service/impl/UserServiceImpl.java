package softuni.unisports.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import softuni.unisports.enums.RoleEnum;
import softuni.unisports.model.entity.RoleEntity;
import softuni.unisports.model.entity.UserEntity;
import softuni.unisports.model.service.UserRegistrationServiceModel;
import softuni.unisports.repository.RoleRepository;
import softuni.unisports.repository.UserRepository;
import softuni.unisports.security.UniSportsUserDetailsService;
import softuni.unisports.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;
    private final UniSportsUserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, ModelMapper modelMapper, UniSportsUserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void seedUsers(List<UserEntity> users) {
        this.userRepository.saveAll(users);
    }

    @Override
    public void registerUser(UserRegistrationServiceModel userRegistrationServiceModel) {
        UserEntity userEntity = modelMapper.map(userRegistrationServiceModel, UserEntity.class);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));

        RoleEntity userRole = roleRepository.findByName(RoleEnum.USER).
                orElseThrow(() -> new IllegalStateException("USER role not found. Please seed the roles."));

        userEntity.addRole(userRole);

        userEntity = userRepository.save(userEntity);

        UserDetails principal = userDetailsService.loadUserByUsername(userEntity.getUsername());

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                principal,
                userEntity.getPassword(),
                principal.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
