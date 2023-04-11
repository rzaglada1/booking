package com.rzaglada1.booking.controllers;


import com.rzaglada1.booking.models.Address;
import com.rzaglada1.booking.models.House;
import com.rzaglada1.booking.models.enams.Role;
import com.rzaglada1.booking.services.HouseService;
import com.rzaglada1.booking.services.UserService;
import com.rzaglada1.booking.services.WishService;
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
    private final UserService userService;
    private final WishService wishService;


    // request form all  house
    @GetMapping
    public String houseAll(Model model, Principal principal) {
        if (principal != null && userService.getUserByPrincipal(principal).getRoles().contains(Role.ROLE_ADMIN)) {
            model.addAttribute("houses", houseService.getAll());
        } else {
            model.addAttribute("houses", houseService.getHouseByUser(userService.getUserByPrincipal(principal)));
        }

        return "house/house_list";
    }

    // request form new  house
    @GetMapping("/new")
    public String houseNew(Model model) {
        model.addAttribute("house", null);
        model.addAttribute("address", new Address());

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
            //check is active
            if (house.getIsAvailableForm() != null) {
                house.setIsAvailable(true);
            } else {
                house.setIsAvailable(false);
            }

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
            , Principal principal
    ) {
        try {
            //check is active
            if (house.getIsAvailableForm() != null) {
                house.setIsAvailable(true);
            } else {
                house.setIsAvailable(false);
            }

            if (principal != null && houseService.getById(id).isPresent()) {
                if (userService.getUserByPrincipal(principal).getRoles().contains(Role.ROLE_ADMIN)) {
                    houseService.update(house, address, file, id);
                }
                if (houseService.getHouseById(id).orElseThrow().getUser().equals(userService.getUserByPrincipal(principal))) {
                    houseService.update(house, address, file, id);
                }

            }


        } catch (IOException e) {
            System.out.println("Something wrong");
        }
        return "redirect:/houses";
    }

    // request form edit house by id
    @GetMapping("/{id}/edit")
    public String houseEdit(@PathVariable Long id, Model model, Principal principal) {

        System.out.println("11111 " + houseService.getById(id).get().getPrice());
        if (principal != null && houseService.getById(id).isPresent()) {
            if (userService.getUserByPrincipal(principal).getRoles().contains(Role.ROLE_ADMIN)
                    || houseService.getHouseById(id).orElseThrow().getUser().equals(userService.getUserByPrincipal(principal))
            ) {
                model.addAttribute("house", houseService.getById(id).get());
                model.addAttribute("address", houseService.getById(id).get().getAddress());
            }
        }
        return "house/house_form";
    }

    @GetMapping("/{id}/delete")
    public String houseDelete(@PathVariable("id") Long id) {
        houseService.deleteById(id);

        return "redirect:/houses";
    }

    @GetMapping("/{houseId}/detail")
    public String houseDetail(@PathVariable("houseId") Long houseId, Model model, Principal principal) {
        if (houseService.getById(houseId).isPresent()) {
            model.addAttribute("house", houseService.getById(houseId).get());
            model.addAttribute("feedbacks", houseService.getById(houseId).get().getFeedbackList());
            model.addAttribute("user", houseService.getById(houseId).get().getUser());
            model.addAttribute("user", userService.getUserByPrincipal(principal));
            if (principal != null) {
                model.addAttribute("isWishList", wishService.existsByHouseIdAndUser(houseId, principal));
            }

        }
        return "house/house_detail";
    }

    @PostMapping("/{houseId}/booking")
    public String hoseBooking (@PathVariable("houseId") long houseId, Principal principal) {

        return " ";
    }




}
