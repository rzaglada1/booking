package com.rzaglada1.booking.controllers;

import com.rzaglada1.booking.models.User;
import com.rzaglada1.booking.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    // request form new  user
    @GetMapping("/new")
    public String userNew(Model model) {
        model.addAttribute("user",null);
        return "user/user_form";
    }

    // create new User
    @PostMapping
    public String users(User user) {
        userService.saveToBase(user);
        return "redirect:/";
    }

    // update user
    @PostMapping("/{id}")
    public String userUpdate(User user, @PathVariable long id) {
        userService.update(user, id);
        return "redirect:/";
    }

    // request form edit user by id
    @GetMapping("/{id}/edit")
    public String userEdit(@PathVariable Long id, Model model) {

        if (userService.getById(id).isPresent()) {

            User user = userService.getById(id).get();
            System.out.println(passwordEncoder.encode(user.getPassword()));

            model.addAttribute("user", userService.getById(id).get());
        }
        return "/user/user_form";
    }

    @GetMapping("/{id}/delete")
    public String userDelete(@PathVariable("id") Long id) {
        userService.deleteById(id);


        return "redirect:/logout";
    }

}
