package softuni.unisports.web;


import org.modelmapper.ModelMapper;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import softuni.unisports.model.binding.UserRegistrationBindingModel;
import softuni.unisports.model.service.UserRegistrationServiceModel;
import softuni.unisports.service.UserService;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final ModelMapper modelMapper;
    private final UserService userService;

    public UsersController(ModelMapper modelMapper, UserService userService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(UserRegistrationBindingModel userRegistrationBindingModel,
                                     BindingResult bindingResult,
                                     RedirectAttributes redirectAttributes) {

        UserRegistrationServiceModel userServiceModel = modelMapper.map(userRegistrationBindingModel, UserRegistrationServiceModel.class);

        //TODO Validation

        userService.registerUser(userServiceModel);

        //TODO if not working properly change method to return String
        return "redirect:/";
    }

    @PostMapping("/login-error")
    public ModelAndView errorLogin(@ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
                                           String username) {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("bad_credentials", true);
        modelAndView.addObject("username", username);
        modelAndView.setViewName("/login");

        return modelAndView;
    }
}
