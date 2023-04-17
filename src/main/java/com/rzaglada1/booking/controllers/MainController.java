package com.rzaglada1.booking.controllers;

import com.rzaglada1.booking.models.enams.Role;
import com.rzaglada1.booking.services.HouseService;
import com.rzaglada1.booking.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.security.Principal;
import java.time.LocalDate;


@Controller
@RequiredArgsConstructor
public class MainController {

    private final HouseService houseService;
    private final UserService userService;


    @GetMapping("/login")
    public String login() {
        return "user/login";
    }


    @GetMapping("/")
    public String index(Model model, Principal principal) {
        if (userService.getUserByPrincipal(principal).getEmail() != null) {
            model.addAttribute("user", userService.getUserByPrincipal(principal));
            if (userService.getUserByPrincipal(principal).getRoles().contains(Role.ROLE_ADMIN)) {
                model.addAttribute("admin", "admin");
            }
        } else if (userService.isEmpty()) {
            return "redirect:/users/new";
        }

        return "index";
    }


    @GetMapping("/find")
    public String houseFind(
              @RequestParam(value = "country", defaultValue = "%") String country
              ,@RequestParam(value = "city", defaultValue = "%") String city
            , @RequestParam(value = "date", defaultValue = "1970-01-01") LocalDate date
            , @RequestParam(value = "days", defaultValue = "1") int days
            , @RequestParam(value = "people", defaultValue = "1") int people
                            , Model model,
              Principal principal
    ) {

        if (userService.getUserByPrincipal(principal).getEmail() != null) {
            model.addAttribute("user", userService.getUserByPrincipal(principal));
            if (userService.getUserByPrincipal(principal).getRoles().contains(Role.ROLE_ADMIN)) {
                model.addAttribute("admin", "admin");
            }
        }

        model.addAttribute("houses", houseService.filterHouses(country, city, date, days, people));


        return "house/house_list_filter" ;
    }

}
