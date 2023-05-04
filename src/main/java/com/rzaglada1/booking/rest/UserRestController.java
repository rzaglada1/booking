package com.rzaglada1.booking.rest;

import com.rzaglada1.booking.models.User;
import com.rzaglada1.booking.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;

    @GetMapping
    public List<User> getAllUsers (Pageable pageable) {
        System.out.println(userService.getAll(pageable).getContent());

        return  userService.getAll(pageable).getContent();
    }


    @GetMapping({"/{id}"})
    @ResponseBody
    public User getUsersById (@PathVariable long id) {
        System.out.println(userService.getById(id).orElseThrow());

        return  userService.getById(id).orElseThrow();
    }
}
