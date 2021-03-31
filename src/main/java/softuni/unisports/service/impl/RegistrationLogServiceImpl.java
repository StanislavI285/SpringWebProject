package softuni.unisports.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.unisports.model.entity.UserEntity;
import softuni.unisports.model.entity.UserRegisterLogEntity;
import softuni.unisports.model.service.UserServiceModel;
import softuni.unisports.model.view.UserListViewModel;
import softuni.unisports.model.view.UserRegisterLogViewModel;
import softuni.unisports.repository.RegistrationLogRepository;
import softuni.unisports.service.RegistrationLogService;
import softuni.unisports.service.UserService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegistrationLogServiceImpl implements RegistrationLogService {

    private final UserService userService;
    private final ModelMapper modelMapper;
    private final RegistrationLogRepository registrationLogRepository;

    public RegistrationLogServiceImpl(UserService userService, ModelMapper modelMapper, RegistrationLogRepository registrationLogRepository) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.registrationLogRepository = registrationLogRepository;
    }


    @Override
    public void createLog(String username, LocalDateTime registerDateAndTime) {
        UserServiceModel userServiceModel = userService.findUserByUsername(username);
        if (userServiceModel != null) {
            UserEntity userEntity = modelMapper.map(userServiceModel, UserEntity.class);
            UserRegisterLogEntity logEntity = new UserRegisterLogEntity();
            logEntity.setUserEntity(userEntity)
                    .setRegistrationDateAndTime(registerDateAndTime);
            registrationLogRepository.save(logEntity);
        }
    }

    @Override
    public List<UserRegisterLogViewModel> getLogsForCurrentMonth() {

        return this.registrationLogRepository.findAllSortedByDate().
                stream().
                map(l -> {
                    UserRegisterLogViewModel logViewModel = modelMapper.map(l, UserRegisterLogViewModel.class);
                    logViewModel.setUser(modelMapper.map(l.getUserEntity(), UserListViewModel.class));
                    return logViewModel;
                }).
                filter(l -> l.getRegistrationDateAndTime().getMonth() == LocalDateTime.now().getMonth()).
                collect(Collectors.toList());
    }

}
