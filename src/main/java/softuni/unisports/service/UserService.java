package softuni.unisports.service;

import softuni.unisports.model.entity.UserEntity;
import softuni.unisports.model.service.UserRegistrationServiceModel;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void seedUsers(List<UserEntity> adminUser);

    void registerUser(UserRegistrationServiceModel userRegistrationServiceModel);

    boolean userExists(String username);

    boolean emailExists(String email);

    UserEntity findUserByUsername(String name);
}
