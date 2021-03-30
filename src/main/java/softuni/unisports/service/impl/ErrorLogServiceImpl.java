package softuni.unisports.service.impl;


import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.unisports.model.entity.ErrorLogEntity;
import softuni.unisports.model.service.ErrorLogServiceModel;
import softuni.unisports.repository.ErrorLogRepository;
import softuni.unisports.service.ErrorLogService;

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
}
