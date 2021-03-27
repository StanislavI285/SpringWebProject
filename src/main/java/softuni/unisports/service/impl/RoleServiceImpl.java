package softuni.unisports.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.unisports.enums.RoleEnum;
import softuni.unisports.model.entity.RoleEntity;
import softuni.unisports.model.service.RoleServiceModel;
import softuni.unisports.repository.RoleRepository;
import softuni.unisports.service.RoleService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    public RoleServiceImpl(RoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public void seedRoles() {
        if (this.roleRepository.count() == 0) {
            List<RoleEntity> rolesList = new ArrayList<>() {
            };
            rolesList.add(new RoleEntity().setName(RoleEnum.ROOT));
            rolesList.add(new RoleEntity().setName(RoleEnum.ADMIN));
            rolesList.add(new RoleEntity().setName(RoleEnum.MODERATOR));
            rolesList.add(new RoleEntity().setName(RoleEnum.USER));
            this.roleRepository.saveAll(rolesList);
        }
    }

    @Override
    public RoleServiceModel getRole(RoleEnum name) {
        RoleEntity roleEntity = this.roleRepository.findByName(name).orElseThrow(() -> new NullPointerException("Please select existing role"));
        return modelMapper.map(roleEntity, RoleServiceModel.class);
    }

}
