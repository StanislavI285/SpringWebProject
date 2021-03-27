package softuni.unisports.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import softuni.unisports.enums.RoleEnum;
import softuni.unisports.model.binding.UserRoleUpdateBindingModel;
import softuni.unisports.service.UserService;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/roles")
public class RolesController {

    private final UserService userService;

    public RolesController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping()
    public String addRole(Model model) {

        if (!model.containsAttribute("userRoleUpdateBindingModel")) {
            model.addAttribute("userRoleUpdateBindingModel", new UserRoleUpdateBindingModel());
            model.addAttribute("nonExistingUsernameError", false);
            model.addAttribute("roleAlreadyPresentError", false);
            model.addAttribute("passwordError", false);
        }

        return "role-management";
    }

    @PostMapping("/add")
    public String addRoleConfirm(@Valid @ModelAttribute UserRoleUpdateBindingModel userRoleUpdateBindingModel,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes,
                                 @AuthenticationPrincipal UserDetails principal) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userRoleUpdateBindingModel", userRoleUpdateBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRoleUpdateBindingModel", bindingResult);
            return "redirect:/roles";
        }

        if (!this.userService.userExists(userRoleUpdateBindingModel.getUsername())) {
            redirectAttributes.addFlashAttribute("userRoleUpdateBindingModel", userRoleUpdateBindingModel);
            redirectAttributes.addFlashAttribute("nonExistingUsernameError", true);
            return "redirect:/roles";
        }


        if (this.userService.getUserRoles(userRoleUpdateBindingModel.getUsername()).contains(userRoleUpdateBindingModel.getRole())) {
            redirectAttributes.addFlashAttribute("userRoleUpdateBindingModel", userRoleUpdateBindingModel);
            redirectAttributes.addFlashAttribute("roleAlreadyPresentError", true);
            return "redirect:/roles";
        }

        if (!this.userService.checkPasswordMatch(principal.getUsername(), userRoleUpdateBindingModel.getAdminPassword())) {
            redirectAttributes.addFlashAttribute("userRoleUpdateBindingModel", userRoleUpdateBindingModel);
            redirectAttributes.addFlashAttribute("passwordError", true);
            return "redirect:/roles";
        }

        this.userService.addUserRole(userRoleUpdateBindingModel.getUsername(), RoleEnum.valueOf(userRoleUpdateBindingModel.getRole()));

        return "redirect:/roles";
    }
}
