package softuni.unisports.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.unisports.model.entity.UserEntity;
import softuni.unisports.model.entity.UserRegisterLogEntity;
import softuni.unisports.repository.LogRepository;
import softuni.unisports.service.LogService;
import softuni.unisports.service.UserService;

import java.time.LocalDateTime;

@Service
public class LogServiceImpl implements LogService {

    private final UserService userService;
    private final ModelMapper modelMapper;
    private final LogRepository logRepository;

    public LogServiceImpl(UserService userService, ModelMapper modelMapper, LogRepository logRepository) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.logRepository = logRepository;
    }


    @Override
    public void createLog(String username, LocalDateTime registerDateAndTime) {
        UserEntity userEntity = modelMapper.map(userService.findUserByUsername(username), UserEntity.class);
        UserRegisterLogEntity logEntity = new UserRegisterLogEntity();
        logEntity.setUserEntity(userEntity)
                .setRegistrationDateAndTime(registerDateAndTime);
        logRepository.save(logEntity);
    }
}
