package softuni.unisports.service;

import softuni.unisports.model.entity.RoleEntity;
import softuni.unisports.model.entity.UserEntity;

import java.util.List;

public interface UserService {
    void seedUsers(List<UserEntity> adminUser);
}
