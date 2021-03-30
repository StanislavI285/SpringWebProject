package softuni.unisports.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import softuni.unisports.model.binding.UserRegistrationBindingModel;
import softuni.unisports.service.LogService;

import java.time.LocalDateTime;

@Aspect
@Component
public class LogAspect {

    private final LogService logService;

    public LogAspect(LogService logService) {
        this.logService = logService;
    }

    @Pointcut("execution(* softuni.unisports.web.UsersController.registerUser(..))")
    public void userRegisterPointcut() {
    }

    @After("userRegisterPointcut()")
    public void afterRegisterAdvice(JoinPoint joinPoint) {

        Object[] args = joinPoint.getArgs();
        UserRegistrationBindingModel userRegistrationBindingModel = (UserRegistrationBindingModel) args[0];
        String username = userRegistrationBindingModel.getUsername();
        LocalDateTime registerDateAndTime = LocalDateTime.now();

        logService.createLog(username, registerDateAndTime);
    }

}
