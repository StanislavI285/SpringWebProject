package softuni.unisports.service;

import softuni.unisports.model.entity.RoleEntity;
import softuni.unisports.model.entity.UserEntity;
import softuni.unisports.model.service.UserRegistrationServiceModel;

import java.util.List;

public interface UserService {
    void seedUsers(List<UserEntity> adminUser);

    void registerUser(UserRegistrationServiceModel userRegistrationServiceModel);
}
