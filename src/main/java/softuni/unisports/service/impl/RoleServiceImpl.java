package softuni.unisports.service.impl;

import org.springframework.stereotype.Service;
import softuni.unisports.enums.RoleEnum;
import softuni.unisports.model.entity.RoleEntity;
import softuni.unisports.repository.RoleRepository;
import softuni.unisports.service.RoleService;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void seedRoles() {
        if (this.roleRepository.count() == 0) {
            List<RoleEntity> rolesList = new ArrayList<>() {
                {
                    add(new RoleEntity().setName(RoleEnum.ADMIN));
                    add(new RoleEntity().setName(RoleEnum.MODERATOR));
                    add(new RoleEntity().setName(RoleEnum.USER));
                }
            };
            this.roleRepository.saveAll(rolesList);
        }
    }

    @Override
    public RoleEntity getRole(RoleEnum name) {
        return this.roleRepository.findByName(name).orElseThrow(() -> new NullPointerException("Please select existing role"));
    }
}
