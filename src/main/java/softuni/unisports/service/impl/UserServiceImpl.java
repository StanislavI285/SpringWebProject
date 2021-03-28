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
import softuni.unisports.model.service.UserServiceModel;
import softuni.unisports.model.view.UserListViewModel;
import softuni.unisports.model.view.UserViewModel;
import softuni.unisports.repository.RoleRepository;
import softuni.unisports.repository.UserRepository;
import softuni.unisports.security.UniSportsUserDetailsService;
import softuni.unisports.service.UserService;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    public void seedUser(UserServiceModel user) {

        UserEntity userEntity = modelMapper.map(user, UserEntity.class);
        RoleEntity rootRole = roleRepository.findByName(RoleEnum.ROOT).get();
        RoleEntity adminRole = roleRepository.findByName(RoleEnum.ADMIN).get();
        RoleEntity moderatorRole = roleRepository.findByName(RoleEnum.MODERATOR).get();
        RoleEntity userRole = roleRepository.findByName(RoleEnum.USER).get();
        userEntity.setRoles(Set.of(rootRole, adminRole, moderatorRole, userRole));
        this.userRepository.save(userEntity);
    }

    @Override
    public void registerUser(UserServiceModel userServiceModel) {
        UserEntity userEntity = modelMapper.map(userServiceModel, UserEntity.class);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));

        RoleEntity userRole = roleRepository.findByName(RoleEnum.USER).
                orElseThrow(() -> new IllegalStateException("USER role not found. Please seed the roles."));

        userEntity.addRole(userRole);
        userEntity.setImageUrl("https://res.cloudinary.com/dhrjhgp37/image/upload/v1616228208/avatar_l23qcd.jpg");

        userEntity = userRepository.save(userEntity);

        UserDetails principal = userDetailsService.loadUserByUsername(userEntity.getUsername());

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                principal,
                userEntity.getPassword(),
                principal.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Override
    public boolean userExists(String username) {
        return this.userRepository.findByUsername(username).isPresent();
    }

    @Override
    public boolean emailExists(String email) {
        return this.userRepository.findByEmail(email).isPresent();
    }

    @Override
    public UserServiceModel findUserByUsername(String name) {
        UserServiceModel userServiceModel = modelMapper.
                map(this.userRepository.findByUsername(name).get(),
                        UserServiceModel.class);
        return userServiceModel;
    }

    @Override
    public void setUserRole(String username, RoleEnum roleEnum) {
        UserEntity user = this.userRepository.
                findByUsername(username).
                orElseThrow(() -> new NullPointerException("No user exists with this username"));

        if (roleEnum.name().equals("ADMIN")) {
            user.setRoles(Set.of(this.roleRepository.findByName(RoleEnum.ADMIN).get(),
                    this.roleRepository.findByName(RoleEnum.MODERATOR).get(),
                    this.roleRepository.findByName(RoleEnum.USER).get()));
        } else if (roleEnum.name().equals("MODERATOR")) {
            user.setRoles(Set.of(this.roleRepository.findByName(RoleEnum.MODERATOR).get(),
                    this.roleRepository.findByName(RoleEnum.USER).get()));
        } else if (roleEnum.name().equals("USER")) {
            user.setRoles(Set.of(this.roleRepository.findByName(RoleEnum.USER).get()));
        }

        this.userRepository.save(user);
    }

    public List<String> getUserRoles(String username) {
        Set<RoleEntity> roleEntities = this.userRepository.findByUsername(username).get().getRoles(); //<-- check for username already made in roles controller
        List<String> rolesAsString = roleEntities.
                stream().
                map(r -> r.getName().toString()).
                collect(Collectors.toList());
        return rolesAsString;
    }

    @Override
    public boolean checkPasswordMatch(String username, String adminPassword) {
        String dbPassword = this.userRepository.findByUsername(username).get().getPassword();
        return passwordEncoder.matches(adminPassword, dbPassword);

    }

    @Override
    public List<UserListViewModel> getAllUsers() {
        return this.userRepository.
                findAll().
                stream().
                map(u -> {
                    UserListViewModel viewModel = modelMapper.map(u, UserListViewModel.class);
                    viewModel.setRoles(u.getRoles().
                            stream().
                            map(r -> r.getName().toString()).
                            sorted().
                            collect(Collectors.toCollection(LinkedHashSet::new)));
                    return viewModel;
                }).
                collect(Collectors.toList());
    }

    @Override
    public UserViewModel findUserById(String id) {
        return modelMapper.map(this.userRepository.findById(id),
                        UserViewModel.class);
    }
}
