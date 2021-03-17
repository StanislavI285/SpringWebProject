package softuni.unisports.web;


import org.modelmapper.ModelMapper;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

import javax.validation.Valid;

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
    public String register(Model model) {

        if (!model.containsAttribute("userRegistrationBindingModel")) {
            model.addAttribute("userRegistrationBindingModel", new UserRegistrationBindingModel());
            model.addAttribute("usernameExistsError", false);
            model.addAttribute("emailExistsError", false);
        }

        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute UserRegistrationBindingModel userRegistrationBindingModel,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {

        UserRegistrationServiceModel userServiceModel = modelMapper.map(userRegistrationBindingModel, UserRegistrationServiceModel.class);

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userRegistrationBindingModel", userRegistrationBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegistrationBindingModel", bindingResult);

            return "redirect:register";
        }


        if (userService.userExists(userRegistrationBindingModel.getUsername())) {
            redirectAttributes.addFlashAttribute("userRegistrationBindingModel", userRegistrationBindingModel);
            redirectAttributes.addFlashAttribute("usernameExistsError", true);

            System.out.println(userService.userExists(userRegistrationBindingModel.getUsername()));
            return "redirect:register";
        }

        if (userService.emailExists(userRegistrationBindingModel.getEmail())) {
            redirectAttributes.addFlashAttribute("userRegistrationBindingModel", userRegistrationBindingModel);
            redirectAttributes.addFlashAttribute("emailExistsError", true);

            return "redirect:register";
        }

        userService.registerUser(userServiceModel);

        return "redirect:/";
    }

    @PostMapping("/login-error")
    public ModelAndView errorLogin(@ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
                                           String username,
                                   RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView();

        redirectAttributes.addFlashAttribute("bad_credentials", true);
        redirectAttributes.addFlashAttribute("username", username);

        modelAndView.setViewName("redirect:/users/login");

        return modelAndView;
    }
}
