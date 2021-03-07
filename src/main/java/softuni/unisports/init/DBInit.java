package softuni.unisports.init;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import softuni.unisports.enums.RoleEnum;
import softuni.unisports.model.entity.RoleEntity;
import softuni.unisports.model.entity.UserEntity;
import softuni.unisports.service.RoleService;
import softuni.unisports.service.UserService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class DBInit implements CommandLineRunner {

    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public DBInit(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;

    }

    @Override
    public void run(String... args) throws Exception {
        initRoles();
        initAdminUser();
    }

    private void initRoles() {
        this.roleService.seedRoles();
    }

    private void initAdminUser() {
        UserEntity adminUser = new UserEntity();
        RoleEntity adminRole = this.roleService.getRole(RoleEnum.ADMIN);
        RoleEntity moderatorRole = this.roleService.getRole(RoleEnum.MODERATOR);
        RoleEntity userRole = this.roleService.getRole(RoleEnum.USER);

        adminUser.
                setUsername("admin").
                setEmail("admin@unisports.com").
                setFirstName("Pesho").
                setLastName("Peshov").
                setPassword(passwordEncoder.encode("12345")).
                setRoles(List.of(adminRole, moderatorRole, userRole)).
                setCreated(LocalDateTime.now()).
                setUpdated(LocalDateTime.now());

        this.userService.seedUsers(List.of(adminUser));
    }
}
