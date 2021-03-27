package softuni.unisports.service;

import softuni.unisports.enums.RoleEnum;
import softuni.unisports.model.entity.RoleEntity;
import softuni.unisports.model.entity.UserEntity;
import softuni.unisports.model.service.UserServiceModel;

import java.util.List;

public interface UserService {
    void seedUser(UserServiceModel userServiceModel);

    void registerUser(UserServiceModel userServiceModel);

    boolean userExists(String username);

    boolean emailExists(String email);

    UserServiceModel findUserByUsername(String name);

    void addUserRole(String username, RoleEnum roleEnum);

    public List<String> getUserRoles(String username);

    boolean checkPasswordMatch(String username, String adminPassword);
}
