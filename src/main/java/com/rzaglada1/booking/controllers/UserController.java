package com.rzaglada1.booking.controllers;

import com.rzaglada1.booking.models.User;
import com.rzaglada1.booking.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    // request form new  user
    @GetMapping("/new")
    public String userNew() {
        return "user/user_new";
    }

    // create new User
    @PostMapping
    public String users(User user) {
        userService.saveToBase(user);
        return "redirect:user/login";
    }

//    @PostMapping
//    public String users(@RequestBody  String user) {
//
//        System.out.println(user);
//        return "user/login";
//    }

    // update user
    @PostMapping("/{id}")
    public String userUpdate(User user, @PathVariable long id) {
        userService.update(user, id);
        return "user/user_new";
    }

    // request form edit user by id
    @GetMapping("/{id}/edit")
    public String userEdit(@PathVariable Long id, Model model) {
        if (userService.getById(id).isPresent()) {
            model.addAttribute("user", userService.getById(id).get());
        }
        return "user/user_edit";
    }


}
