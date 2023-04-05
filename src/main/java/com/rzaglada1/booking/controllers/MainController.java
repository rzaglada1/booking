package com.rzaglada1.booking.controllers;

import com.rzaglada1.booking.services.AddressService;
import com.rzaglada1.booking.services.HouseService;
import com.rzaglada1.booking.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.time.LocalDate;


@Controller
@RequiredArgsConstructor
public class MainController {

    private final AddressService addressService;
    private final HouseService houseService;
    private final UserService userService;


    @GetMapping("/login")
    public String login() {
        return "user/login";
    }


    @GetMapping("/")
    public String index(Model model, Principal principal) {
        model.addAttribute("user", userService.getUserByPrincipal(principal));

        return "index";
    }


    @GetMapping("/find")
    public String houseFind(@RequestParam(value = "destination", defaultValue = "-1") String destination
            , @RequestParam(value = "date", defaultValue = "1970-01-01") LocalDate date
            , @RequestParam(value = "days", defaultValue = "1") int days
            , @RequestParam(value = "people", defaultValue = "1") int people
                            , Model model
    ) {

        model.addAttribute("houses", houseService.filterHouses(destination, date, days, people));


        return "house/house_list_filter" ;
    }

}
