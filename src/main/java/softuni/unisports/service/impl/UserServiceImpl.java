package softuni.unisports.service.impl;

import org.springframework.stereotype.Service;
import softuni.unisports.model.entity.UserEntity;
import softuni.unisports.repository.UserRepository;
import softuni.unisports.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void seedUsers(List<UserEntity> adminUser) {
        this.userRepository.saveAll(adminUser);
    }
}
