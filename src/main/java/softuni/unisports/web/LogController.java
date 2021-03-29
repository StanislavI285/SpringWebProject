package softuni.unisports.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import softuni.unisports.model.view.UserRegisterLogViewModel;
import softuni.unisports.service.LogService;

import java.util.List;

@Controller
@RequestMapping("/logs")
public class LogController {

    private final LogService logService;

    public LogController(LogService logService) {
        this.logService = logService;
    }


    @GetMapping("/new-users")
    public String getNewUsersLog(Model model) {

        List<UserRegisterLogViewModel> newRegistrations = this.logService.getLogsForCurrentMonth();
        model.addAttribute("newRegistrations", newRegistrations);

        return "new-users-log";
    }


}
