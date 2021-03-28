package softuni.unisports.service;

import java.time.LocalDateTime;

public interface LogService {
    void createLog(String username, LocalDateTime registerDateAndTime);
}
