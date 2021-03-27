package softuni.unisports.model.binding;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UserRoleUpdateBindingModel {
    private String username;
    private String role;
    private String adminPassword;

    public UserRoleUpdateBindingModel() {
    }

    @NotBlank
    public String getUsername() {
        return username;
    }

    public UserRoleUpdateBindingModel setUsername(String username) {
        this.username = username;
        return this;
    }

    @NotNull
    public String getRole() {
        return role;
    }

    public UserRoleUpdateBindingModel setRole(String role) {
        this.role = role;
        return this;
    }

    @NotNull
    public String getAdminPassword() {
        return adminPassword;
    }

    public UserRoleUpdateBindingModel setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
        return this;
    }
}
