package com.rzaglada1.booking.controllers;

import com.rzaglada1.booking.models.User;
import com.rzaglada1.booking.models.enams.Role;
import com.rzaglada1.booking.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    // request form new  user
    @GetMapping("/new")
    public String userNew(Model model) {
        model.addAttribute("new", " ");
        model.addAttribute("user", null);
        return "user/user_form";
    }

    // create new User
    @PostMapping
    public String users(
            @Valid User user
            , BindingResult bindingResult
            , Model model
    ) {
        model.addAttribute("new", " ");
        if(bindingResult.hasErrors()) {
            return getString(user, bindingResult, model);
        } else {

            if (!user.getPassword().equals(user.getPasswordConfirm())) {
                model.addAttribute("errorMessage", " ");
                model.addAttribute("errorMessagePass", "Паролі не співпадають");
                return "user/user_form";
            }

            if (!userService.saveToBase(user)) {
                model.addAttribute("errorMessage", " ");
                model.addAttribute("errorMessageDouble", "Користувач з таким email вже існує");
                model.addAttribute("user", user);
                return "user/user_form";
            }
        }
        return "redirect:/";
    }


    // update user
    @PostMapping(value = "/update")
    public String userUpdate(
            @Valid User user
            , BindingResult bindingResult
            , Model model
            , Principal principal
    ) {

        model.addAttribute("edit", " ");
        if (bindingResult.hasErrors()) {
            return getString(user, bindingResult, model);
        } else {
            if (userService.getUserByPrincipal(principal) != null) {
                long userId = userService.getUserByPrincipal(principal).getId();
                if (userService.getUserByPrincipal(principal).getRoles().contains(Role.ROLE_ADMIN)) {
                    model.addAttribute("admin", "admin");
                }
                //check is active
                if (userService.getUserByPrincipal(principal).getActive()) {
                    user.setActive(true);
                } else {
                    user.setActive(false);
                }

                if (user.getPasswordOld() != null && user.getPasswordOld().length() != 0
                        && !userService.isTruePassword(userId, user.getPasswordOld())) {
                    model.addAttribute("errorMessage", ".");
                    model.addAttribute("errorMessagePassOld", "Діючий пароль не вірний");
                    return "user/user_form";
                }

                if (!user.getPassword().equals(user.getPasswordConfirm())) {
                    model.addAttribute("errorMessage", " ");
                    model.addAttribute("errorMessagePass", "Паролі не співпадають");
                    return "user/user_form";
                }

                userService.update(user, userId);
            }
        }
        return "redirect:/";
    }





    // update user for admin
    @PostMapping(value = "/update/{id}")
    public String userUpdate(
            @Valid User user
            , BindingResult bindingResult
            , Model model
            , Principal principal
            , @PathVariable(required = false) String id
    ) {
        if (userService.getUserByPrincipal(principal) != null
                && userService.getUserByPrincipal(principal).getRoles().contains(Role.ROLE_ADMIN)) {
            long userId = Long.parseLong(id);
            model.addAttribute("edit", " ");
            model.addAttribute("idPresent", " ");
            model.addAttribute("admin", "admin");
            model.addAttribute("rolesEnum", Arrays.asList(Role.values()));
            //check is active
            if (user.getActiveForm() != null) {
                user.setActive(true);
            } else {
                user.setActive(false);
            }
            // check role
            Set<Role> roles = userService.getById(userId).orElseThrow().getRoles();
            user.setRoles(roles);

            if (bindingResult.hasErrors()) {
                return getString(user, bindingResult, model);
            } else {
                    if (user.getPasswordOld() != null && user.getPasswordOld().length() != 0
                            && !userService.isTruePassword(userId, user.getPasswordOld())) {
                        model.addAttribute("errorMessage", " ");
                        model.addAttribute("errorMessagePassOld", "Діючий пароль не вірний");
                        return "user/user_form";
                    }

                    if (!user.getPassword().equals(user.getPasswordConfirm())) {
                        model.addAttribute("errorMessage", " ");
                        model.addAttribute("errorMessagePass", "Паролі не співпадають");

                        return "user/user_form";
                    }

                    userService.update(user, userId);
            }
        }
        return "redirect:/";
    }


    private String getString(@Valid User user, BindingResult bindingResult, Model model) {
        Map<String, String> errorsMap = bindingResult.getFieldErrors().stream().collect(
                Collectors.toMap(fieldError -> fieldError.getField() + "Error", FieldError::getDefaultMessage));

        model.addAttribute("errorMessage", " ");
        model.mergeAttributes(errorsMap);
        model.addAttribute("user", user);
        return "user/user_form";
    }

    // request form edit user by id
    @GetMapping(value = {"/edit", "/edit/{id}"})
    public String userEdit(Model model, Principal principal, @PathVariable(required = false) String id) {

        if (userService.getUserByPrincipal(principal) != null) {
            User user;
            model.addAttribute("edit", " ");

            if (userService.getUserByPrincipal(principal).getRoles().contains(Role.ROLE_ADMIN) && id != null) {
                long userId = Long.parseLong(id);
                user = userService.getById(userId).orElseThrow();
                model.addAttribute("rolesEnum", Arrays.asList(Role.values()));
                model.addAttribute("admin", "admin");
                model.addAttribute("idPresent", " ");
            } else {
                user = userService.getUserByPrincipal(principal);
            }
            user.setPasswordConfirm(user.getPassword());
            model.addAttribute("user", user);
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
    public String userList(Model model, Principal principal, @PageableDefault( size = 3, page = 0) Pageable pageable) {
        if (principal != null && userService.getUserByPrincipal(principal).getRoles().contains(Role.ROLE_ADMIN)) {
            model.addAttribute("admin", "admin");
            model.addAttribute("user", userService.getUserByPrincipal(principal));

            Page<User> userPage;
            userPage = userService.getAll(pageable);
            model.addAttribute("page", userPage);
            model.addAttribute("url", "/users");
        }
        return "user/user_list";
    }


}

