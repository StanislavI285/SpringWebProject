package softuni.unisports.model.view;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class UserRegisterLogViewModel {

    private UserListViewModel user;
    private LocalDateTime registrationDateAndTime;

    public UserRegisterLogViewModel() {
    }

    public UserListViewModel getUser() {
        return user;
    }

    public UserRegisterLogViewModel setUser(UserListViewModel user) {
        this.user = user;
        return this;
    }

    @DateTimeFormat(pattern="yyyy-MM-dd / HH:mm")
    public LocalDateTime getRegistrationDateAndTime() {
        return registrationDateAndTime;
    }

    public UserRegisterLogViewModel setRegistrationDateAndTime(LocalDateTime registrationDateAndTime) {
        this.registrationDateAndTime = registrationDateAndTime;
        return this;
    }
}
