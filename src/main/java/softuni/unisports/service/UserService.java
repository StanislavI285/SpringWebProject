package softuni.unisports.service;

import softuni.unisports.model.entity.UserEntity;
import softuni.unisports.model.service.UserServiceModel;

import java.util.List;

public interface UserService {
    void seedUsers(List<UserEntity> adminUser);

    void registerUser(UserServiceModel userServiceModel);

    boolean userExists(String username);

    boolean emailExists(String email);

    UserServiceModel findUserByUsername(String name);
}
