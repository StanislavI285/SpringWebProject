package softuni.unisports.service;

import softuni.unisports.enums.RoleEnum;
import softuni.unisports.model.entity.RoleEntity;
import softuni.unisports.model.service.RoleServiceModel;

import java.util.List;

public interface RoleService {
    void seedRoles();

    RoleServiceModel getRole(RoleEnum name);

}
