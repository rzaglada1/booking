package com.rzaglada1.booking.controllers;

import com.rzaglada1.booking.services.AddressService;
import com.rzaglada1.booking.services.HouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
@RequiredArgsConstructor
public class MainController {

    private final AddressService addressService;
    private final HouseService houseService;


    @GetMapping("/login")
    public String login () {
        return "user/login";
    }





    @GetMapping("/")
    public String products (Model model) {
        //model.addAttribute("products", productService.listProducts());

        return "house/add_image";
    }


    @GetMapping("/delete")
    public String products () {
        //scheduleService.deleteSchedule(1L);
        houseService.deleteById(2);
        return "index";
    }

    @GetMapping("/all")
    public String productsAll () {


        return "index";
    }
}
