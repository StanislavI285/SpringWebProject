package softuni.unisports.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.unisports.model.entity.UserEntity;
import softuni.unisports.model.entity.UserRegisterLogEntity;
import softuni.unisports.model.view.UserListViewModel;
import softuni.unisports.model.view.UserRegisterLogViewModel;
import softuni.unisports.repository.LogRepository;
import softuni.unisports.service.LogService;
import softuni.unisports.service.UserService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<UserRegisterLogViewModel> getLogsForCurrentMonth() {

        return this.logRepository.findAllSortedByDate().
                stream().
                map(l -> {
                    UserRegisterLogViewModel logViewModel = modelMapper.map(l, UserRegisterLogViewModel.class);
                    logViewModel.setUser(modelMapper.map(l.getUserEntity(), UserListViewModel.class));
                    return logViewModel;
                }).
                filter(l -> l.getRegistrationDateAndTime().getMonth() == LocalDateTime.now().getMonth()).
                collect(Collectors.toList());
    }

    @Override
    public void createErrorLog(String action, LocalDateTime errorDateAndTime) {

    }
}
