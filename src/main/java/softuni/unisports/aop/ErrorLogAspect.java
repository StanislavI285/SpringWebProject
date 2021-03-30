package softuni.unisports.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import softuni.unisports.model.service.ErrorLogServiceModel;
import softuni.unisports.service.ErrorLogService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Aspect
@Component
public class ErrorLogAspect {

    private final ErrorLogService errorLogService;

    public ErrorLogAspect(ErrorLogService errorLogService) {
        this.errorLogService = errorLogService;
    }

    @Pointcut("execution(* softuni.unisports.web.GlobalExceptionHandler.*(..))")
    public void exceptionLogPointcut() {
    }

    @After("exceptionLogPointcut()")
    public void newsViewAdvice(JoinPoint joinpoint) {

        Object[] args = joinpoint.getArgs();
        HttpServletRequest request = (HttpServletRequest) args[0];
        Object ex = args[1];
        String action = joinpoint.getSignature().getName();

        ErrorLogServiceModel error = new ErrorLogServiceModel();
        error.setException(ex.toString())
                .setReason(request.getRequestURI())
                .setDate(LocalDateTime.now());

        errorLogService.logError(error);

    }



}
