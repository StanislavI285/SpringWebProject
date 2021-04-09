package softuni.unisports.web;


import org.modelmapper.ModelMapper;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import softuni.unisports.model.binding.UserEditBindingModel;
import softuni.unisports.model.binding.UserRegistrationBindingModel;
import softuni.unisports.model.service.UserServiceModel;
import softuni.unisports.model.view.UserListViewModel;
import softuni.unisports.model.view.UserViewModel;
import softuni.unisports.service.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

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

    @GetMapping("/profile/show/{username}")
    public String userProfile(@PathVariable String username, Model model, Principal principal) {

        if (!username.equals(principal.getName())) {
            return "redirect:/";
        }

        UserViewModel user = modelMapper.map(this.userService.findUserByUsername(username), UserViewModel.class);
        model.addAttribute("userView", user);

        return "user-profile";
    }

    @GetMapping("/profile/changePassword/{username}")
    public String editProfile(@PathVariable String username, Model model, Principal principal) {

        if (!username.equals(principal.getName())) {
            return "redirect:/";
        }

        if (!model.containsAttribute("userEditBindingModel")) {
            model.addAttribute("userEditBindingModel", new UserEditBindingModel());
        }


        UserViewModel user = modelMapper.map(this.userService.findUserByUsername(username), UserViewModel.class);
        model.addAttribute("userView", user);


        return "change-password";
    }



    @GetMapping("/details/{id}")
    public String userDetails(@PathVariable String id, Model model) {

        UserViewModel user = modelMapper.map(this.userService.findUserById(id), UserViewModel.class);
        model.addAttribute("userView", user);


        return "user-details";
    }

    @GetMapping("/list")
    public String listAllUsers(Model model) {

        List<UserListViewModel> allUsers = this.userService.getAllUsers();

        model.addAttribute("allUsers", allUsers);

        return "all-users";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute UserRegistrationBindingModel userRegistrationBindingModel,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {


        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userRegistrationBindingModel", userRegistrationBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegistrationBindingModel", bindingResult);

            return "redirect:register";
        }


        if (userService.userExists(userRegistrationBindingModel.getUsername())) {
            redirectAttributes.addFlashAttribute("userRegistrationBindingModel", userRegistrationBindingModel);
            redirectAttributes.addFlashAttribute("usernameExistsError", true);
            return "redirect:register";
        }

        if (userService.emailExists(userRegistrationBindingModel.getEmail())) {
            redirectAttributes.addFlashAttribute("userRegistrationBindingModel", userRegistrationBindingModel);
            redirectAttributes.addFlashAttribute("emailExistsError", true);

            return "redirect:register";
        }

        UserServiceModel userServiceModel = modelMapper.map(userRegistrationBindingModel, UserServiceModel.class);
        userService.registerUser(userServiceModel);

        return "redirect:/";
    }


    @PostMapping("/profile/changePassword/{username}")
    public String editProfileConfirm(@Valid @ModelAttribute UserEditBindingModel userEditBindingModel, BindingResult bindingResult,
                                     RedirectAttributes redirectAttributes, @PathVariable String username, Principal principal) {

        if (!username.equals(principal.getName())) {
            return "redirect:/";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userEditBindingModel", userEditBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userEditBindingModel", bindingResult);
            return "redirect:/users/profile/changePassword/" + username;
        }

        System.out.println();
        //TODO finish post mapping


        return "redirect:/users/profile/show/" + username;
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
