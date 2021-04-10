package softuni.unisports.model.binding;

import org.springframework.web.multipart.MultipartFile;
import softuni.unisports.model.validators.fieldMatch.FieldMatch;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@FieldMatch(
        first = "password",
        second = "confirmPassword",
        message = "Passwords are not matching."
)
public class UserEditBindingModel {
    private String username;
    private MultipartFile image;
    private String password;
    private String confirmPassword;


    public UserEditBindingModel() {
    }

    public String getUsername() {
        return username;
    }

    public UserEditBindingModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public MultipartFile getImage() {
        return image;
    }

    public UserEditBindingModel setImage(MultipartFile image) {
        this.image = image;
        return this;
    }

    @NotBlank
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")
    public String getPassword() {
        return password;
    }

    public UserEditBindingModel setPassword(String password) {
        this.password = password;
        return this;
    }

    @NotBlank
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserEditBindingModel setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }
}
