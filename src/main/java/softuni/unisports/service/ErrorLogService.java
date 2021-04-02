package softuni.unisports.service;

import softuni.unisports.model.service.ErrorLogServiceModel;
import softuni.unisports.model.view.ErrorsLogViewModel;

import java.util.List;

public interface ErrorLogService {
    void logError(ErrorLogServiceModel error);

    List<ErrorsLogViewModel> getErrorsForCurrentDate();
}
