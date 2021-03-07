package softuni.unisports.service;

import softuni.unisports.enums.RoleEnum;
import softuni.unisports.model.entity.RoleEntity;

import java.util.List;

public interface RoleService {
    void seedRoles();

    RoleEntity getRole(RoleEnum name);
}
