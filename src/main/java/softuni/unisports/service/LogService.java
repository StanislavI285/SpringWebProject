package softuni.unisports.service;

import softuni.unisports.model.view.UserListViewModel;
import softuni.unisports.model.view.UserRegisterLogViewModel;

import java.time.LocalDateTime;
import java.util.List;

public interface LogService {
    void createLog(String username, LocalDateTime registerDateAndTime);

    List<UserRegisterLogViewModel> getLogsForCurrentMonth();


}
