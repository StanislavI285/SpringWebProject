package softuni.unisports.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import softuni.unisports.model.view.ErrorsLogViewModel;
import softuni.unisports.model.view.UserRegisterLogViewModel;
import softuni.unisports.service.ErrorLogService;
import softuni.unisports.service.RegistrationLogService;

import java.util.List;

@Controller
@RequestMapping("/logs")
public class LogController {

    private final RegistrationLogService registrationLogService;
    private final ErrorLogService errorLogService;

    public LogController(RegistrationLogService registrationLogService, ErrorLogService errorLogService) {
        this.registrationLogService = registrationLogService;
        this.errorLogService = errorLogService;
    }


    @GetMapping("/new-users")
    public String getNewUsersLog(Model model) {

        List<UserRegisterLogViewModel> newRegistrations = this.registrationLogService.getLogsForCurrentMonth();
        model.addAttribute("newRegistrations", newRegistrations);

        return "new-users-log";
    }

    @GetMapping("/errors")
    public String getErrorsLog(Model model) {

        List<ErrorsLogViewModel> errors = this.errorLogService.getErrorsForCurrentDate();
        model.addAttribute("errors", errors);

        return "errors-log";
    }


}
