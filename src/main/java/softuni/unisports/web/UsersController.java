package softuni.unisports.web;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import softuni.unisports.model.binding.UserLoginBindingModel;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UsersController {

    @GetMapping("/login")
    public ModelAndView login(ModelAndView modelAndView) {
        modelAndView.setViewName("login");
        return modelAndView;

    }


    @PostMapping("/login")
    public ModelAndView loginConfirm(@Valid UserLoginBindingModel userLoginBindingModel) {
        return null;

    }

    @GetMapping("/register")
    public ModelAndView register(ModelAndView modelAndView) {
        modelAndView.setViewName("register");
        return modelAndView;

    }
}
