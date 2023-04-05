package com.rzaglada1.booking.controllers;


import com.rzaglada1.booking.models.Address;
import com.rzaglada1.booking.models.House;
import com.rzaglada1.booking.services.HouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;


@Controller
@RequestMapping(value = "/houses")
@RequiredArgsConstructor
public class HouseController {
    private final HouseService houseService;


    // request form all  house
    @GetMapping
    public String houseAll(Model model) {
        //houseService.getAll().forEach(System.out::println);
        model.addAttribute("houses", houseService.getAll());
        return "house/house_list";
    }

    // request form new  house
    @GetMapping("/new")
    public String houseNew(Model model) {
        model.addAttribute("house", null);
        model.addAttribute("address", new Address());
//        model.addAttribute("image", new Image());
        return "house/house_form";
    }


    // create new house
    @PostMapping
    public String house(@RequestParam(value = "file", required = false) MultipartFile file
            , House house
            , Address address
            , Principal principal
    ) {
        try {
            houseService.saveToBase(principal, house, address, file);
        } catch (IOException e) {
            System.out.println("Something wrong");
        }
        return "redirect:/houses";
    }

    // update house
    @PostMapping("/{id}")
    public String houseUpdate(
            @RequestParam(value = "file", required = false) MultipartFile file
            , House house
            , Address address
            , @PathVariable long id
            , @RequestParam(defaultValue = "false") boolean isAvailable
    ) {
        try {
            house.setIsAvailable(isAvailable);
            houseService.update(house, address, file, id);
        } catch (IOException e) {
            System.out.println("Something wrong");
        }
        return "redirect:/houses";
    }

    // request form edit house by id
    @GetMapping("/{id}/edit")
    public String houseEdit(@PathVariable Long id, Model model) {
        if (houseService.getById(id).isPresent()) {
            model.addAttribute("house", houseService.getById(id).get());
            model.addAttribute("address", houseService.getById(id).get().getAddress());
        }
        return "house/house_form";
    }

    @GetMapping("/{id}/delete")
    public String houseDelete(@PathVariable("id") Long id) {
        houseService.deleteById(id);

        return "redirect:/houses";
    }

    @GetMapping("/{id}/detail")
    public String houseDetail(@PathVariable("id") Long id, Model model) {
        if (houseService.getById(id).isPresent()) {
            model.addAttribute("house", houseService.getById(id).get());
        }
        return "house/house_detail";
    }


}