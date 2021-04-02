package softuni.unisports.service.impl;


import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import softuni.unisports.model.entity.ErrorLogEntity;
import softuni.unisports.model.service.ErrorLogServiceModel;
import softuni.unisports.model.view.ErrorsLogViewModel;
import softuni.unisports.repository.ErrorLogRepository;
import softuni.unisports.service.ErrorLogService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ErrorLogServiceImpl implements ErrorLogService {

    private final ErrorLogRepository errorLogRepository;
    private final ModelMapper modelMapper;

    public ErrorLogServiceImpl(ErrorLogRepository errorLogRepository, ModelMapper modelMapper) {
        this.errorLogRepository = errorLogRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public void logError(ErrorLogServiceModel error) {
        this.errorLogRepository.save(modelMapper.map(error, ErrorLogEntity.class));
    }

    @Override
    public List<ErrorsLogViewModel> getErrorsForCurrentDate() {
        return this.errorLogRepository.findAll().
                stream().map(e -> modelMapper.map(e, ErrorsLogViewModel.class)).
                collect(Collectors.toList());
    }


    //   * <li>second</li>
    //	 * <li>minute</li>
    //	 * <li>hour</li>
    //	 * <li>day of month</li>
    //	 * <li>month</li>
    //	 * <li>day of week</li>

    //At 5:00 every day
    @Scheduled(cron = "* 0 5 * * *")
    public void clearErrorLog() {
        this.errorLogRepository.deleteAll();
    }
}
