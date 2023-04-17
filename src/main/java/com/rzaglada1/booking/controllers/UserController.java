package com.rzaglada1.booking.controllers;

import com.rzaglada1.booking.models.User;
import com.rzaglada1.booking.models.enams.Role;
import com.rzaglada1.booking.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.Arrays;

@Controller
@RequestMapping(value = "/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    // request form new  user
    @GetMapping("/new")
    public String userNew(Model model) {
        model.addAttribute("user", null);
        return "user/user_form";
    }

    // create new User
    @PostMapping
    public String users(User user, Model model) {
        System.out.println(user.getPassword());
        System.out.println(user.getPasswordConfirm());
        if (!user.getPassword().equals(user.getPasswordConfirm())) {
            model.addAttribute("errorMessage", " ");
            model.addAttribute("errorMessagePass", "Паролі не співпадають");
            return "user/user_form";
        }

        if (!userService.saveToBase(user)) {
            model.addAttribute("errorMessage", " ");
            model.addAttribute("errorMessageDouble", "Користувач з таким email вже існує");
            return "user/user_form";
        }

        return "redirect:/";
    }

    // update user
    @PostMapping(value = {"/update", "/update/{id}"})
    public String userUpdate(User user, Principal principal, @PathVariable(required = false) String id) {

        if (userService.getUserByPrincipal(principal) != null) {
            long userId = userService.getUserByPrincipal(principal).getId();
            if (userService.getUserByPrincipal(principal).getRoles().contains(Role.ROLE_ADMIN) && id != null) {
                userId = Long.parseLong(id);
            }
            //check is active
            if (user.getActiveForm() != null) {
                user.setActive(true);
            } else {user.setActive(false);}

            userService.update(user, userId);
        }
        return "redirect:/";
    }

    // request form edit user by id
    @GetMapping(value = {"/edit", "/edit/{id}"})
    public String userEdit(Model model, Principal principal, @PathVariable(required = false) String id) {
        if (userService.getUserByPrincipal(principal) != null) {
            if (userService.getUserByPrincipal(principal).getRoles().contains(Role.ROLE_ADMIN) && id != null) {
                long userId = Long.parseLong(id);
                model.addAttribute("user", userService.getById(userId).get());
                model.addAttribute("rolesEnum", Arrays.asList(Role.values()));
                model.addAttribute("admin", "admin");
            } else {
                model.addAttribute("user", userService.getUserByPrincipal(principal));
            }
        }
        return "/user/user_form";
    }

    @GetMapping(value = {"/delete", "/delete/{id}"})
    public String userDelete(Principal principal, @PathVariable(required = false) String id) {
        if (userService.getUserByPrincipal(principal) != null) {
            long userId = userService.getUserByPrincipal(principal).getId();
            if (userService.getUserByPrincipal(principal).getRoles().contains(Role.ROLE_ADMIN) && id != null) {
                userId = Long.parseLong(id);
            }
            try {
                userService.deleteById(userId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "redirect:/logout";
    }

    @GetMapping
    public String userList(Model model, Principal principal) {
        if (principal != null && userService.getUserByPrincipal(principal).getRoles().contains(Role.ROLE_ADMIN)) {
            model.addAttribute("admin", "admin");
            model.addAttribute("user", userService.getUserByPrincipal(principal));
            model.addAttribute("users", userService.getAll());
        }
        return "user/user_list";
    }


}

