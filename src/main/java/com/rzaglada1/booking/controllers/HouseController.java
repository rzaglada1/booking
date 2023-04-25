package com.rzaglada1.booking.controllers;


import com.rzaglada1.booking.models.Address;
import com.rzaglada1.booking.models.House;
import com.rzaglada1.booking.models.OrderHistory;
import com.rzaglada1.booking.models.enams.Role;
import com.rzaglada1.booking.services.HouseService;
import com.rzaglada1.booking.services.OrderHistoryService;
import com.rzaglada1.booking.services.UserService;
import com.rzaglada1.booking.services.WishService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    private final OrderHistoryService orderHistoryService;


    // request form all  house

    @GetMapping
    public String houseAll(Model model
            , Principal principal
            , @PageableDefault( sort = {"id"}, direction = Sort.Direction.ASC, size = 3, page = 0) Pageable pageable) {

        Page<House> housePage;
        if (principal != null) {
            if( userService.getUserByPrincipal(principal).getRoles().contains(Role.ROLE_ADMIN)) {
                housePage = houseService.getAll(pageable);
                model.addAttribute("admin", "admin");
            } else {
                housePage = houseService.getHouseByUser(userService.getUserByPrincipal(principal), pageable);
            }
            model.addAttribute("houses", housePage);
            model.addAttribute("page", housePage);
            model.addAttribute("url", "/houses");

            model.addAttribute("user", userService.getUserByPrincipal(principal));
            if (housePage.getTotalElements() == 0) {
                model.addAttribute("message", "Поки нічого не має.");
                return "/message";
            }
        }


        return "house/house_list";
    }


    // request form new  house
    @GetMapping("/new")
    public String houseNew(Model model, Principal principal) {
        model.addAttribute("house", null);
        model.addAttribute("address", new Address());
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        if (userService.getUserByPrincipal(principal).getRoles().contains(Role.ROLE_ADMIN)) {
            model.addAttribute("admin", "admin");
        }

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

        if (principal != null && houseService.getById(id).isPresent()) {
            if (userService.getUserByPrincipal(principal).getRoles().contains(Role.ROLE_ADMIN)
                    || houseService.getHouseById(id).orElseThrow().getUser().equals(userService.getUserByPrincipal(principal))
            ) {
                model.addAttribute("house", houseService.getById(id).get());
                model.addAttribute("address", houseService.getById(id).get().getAddress());
                model.addAttribute("user", userService.getUserByPrincipal(principal));
            }
            if (userService.getUserByPrincipal(principal).getRoles().contains(Role.ROLE_ADMIN)) {
                model.addAttribute("admin", "admin");
            }
        }
        return "house/house_form";
    }

    @GetMapping("/{id}/delete")
    public String houseDelete(@PathVariable("id") Long id, Principal principal) {
        if (principal != null && houseService.getById(id).isPresent()) {
            if (userService.getUserByPrincipal(principal).getRoles().contains(Role.ROLE_ADMIN)
                    || houseService.getHouseById(id).orElseThrow().getUser().equals(userService.getUserByPrincipal(principal))
            ) {
                houseService.deleteById(id);
            }
        }

        return "redirect:/houses";
    }

    @GetMapping("/{houseId}/detail")
    public String houseDetail(@PathVariable("houseId") Long houseId, Model model, Principal principal) {
        if (houseService.getById(houseId).isPresent()) {
            House house = houseService.getById(houseId).get();
            if (!orderHistoryService.findOrdersByHouseForFree(house).isEmpty()) {
                model.addAttribute("orders", orderHistoryService.findOrdersByHouseForFree(house));
            }
            model.addAttribute("house", house);
            model.addAttribute("feedbacks", house.getFeedbackList());

            if (principal != null) {
                if (userService.getUserByPrincipal(principal).getRoles().contains(Role.ROLE_ADMIN)) {
                    model.addAttribute("admin", "admin");
                }
                model.addAttribute("user", userService.getUserByPrincipal(principal));
                model.addAttribute("isWishList", wishService.existsByHouseIdAndUser(houseId, principal));
            }
        }
        return "house/house_detail";
    }

    @PostMapping("/{houseId}/prebooking")
    public String hosePreBooking(
            @PathVariable("houseId") long houseId,
            OrderHistory orderHistory,
            Model model,
            Principal principal) {

        if (houseService.isDateFree(orderHistory, houseId)) {

            House house = houseService.getHouseById(houseId).get();
            orderHistory.setHouse(house);
            model.addAttribute("house", house);
            model.addAttribute("order", orderHistory);

            return "/house/house_booking";
        } else {
            model.addAttribute("message", "На вказаний період вже заброньовано. Спробуйте інші дати");
            return "/message";
        }
    }

    @PostMapping("/{houseId}/booking")
    public String hoseBooking(
            @PathVariable("houseId") long houseId,
            OrderHistory orderHistory,
            Model model,
            Principal principal) {

        if (houseService.getHouseById(houseId).isPresent() && principal != null) {
            House house = houseService.getHouseById(houseId).get();
            orderHistory.setHouse(house);
            orderHistoryService.saveToBase(orderHistory, houseId, principal);
            model.addAttribute("message", "Заброньовано");
        } else {
            model.addAttribute("message", "Щось пішло не так. Спробуйте знову.");
        }

        return "/message";

    }


}
