package softuni.unisports.service;

import softuni.unisports.model.service.ErrorLogServiceModel;

public interface ErrorLogService {
    void logError(ErrorLogServiceModel error);
}
