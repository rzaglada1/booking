package com.rzaglada1.booking.controllers;

import com.rzaglada1.booking.models.House;
import com.rzaglada1.booking.models.HousesFilter;
import com.rzaglada1.booking.models.enams.Role;
import com.rzaglada1.booking.services.HouseService;
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
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Controller
@RequiredArgsConstructor
public class MainController {

    private final HouseService houseService;
    private final UserService userService;


    @GetMapping("/login")
    public String login(Model model, @RequestParam(name = "error", required = false) String message) {
        if (message != null) {
            model.addAttribute("errorMessage", "Не вірний пароль або користувач");
        }
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

    @PostMapping("/find")
    public String houseFind(
              @Valid  HousesFilter housesFilter
            , BindingResult bindingResult
            , @RequestParam(value = "country", defaultValue = "%") String country
            , @RequestParam(value = "city", defaultValue = "%") String city
            , @RequestParam(value = "date", defaultValue = "1970-01-01") LocalDate date
            , @RequestParam(value = "days", defaultValue = "1") int days
            , @RequestParam(value = "people", defaultValue = "1") int people
            , Model model
            , Principal principal
            , @PageableDefault( size = 3, page = 0) Pageable pageable
    ) {

        housesFilter.setCountry(country);
        housesFilter.setCity(city);
        housesFilter.setDate(date);
        housesFilter.setDays(days);
        housesFilter.setPeople(people);

        if (bindingResult.hasErrors()) {
            return getString(housesFilter, bindingResult, model);
        } else {

        if (userService.getUserByPrincipal(principal).getEmail() != null) {
            model.addAttribute("user", userService.getUserByPrincipal(principal));
            if (userService.getUserByPrincipal(principal).getRoles().contains(Role.ROLE_ADMIN)) {
                model.addAttribute("admin", "admin");
            }
        }
        Page<House> housePage;
        housePage = houseService.filterHouses(country, city, date, days, people,pageable);
        model.addAttribute("page", housePage);
        model.addAttribute("url", "/find?");

        if (housePage.getTotalElements() == 0) {
            model.addAttribute("message", "Нічого не знайдено. Спробуйте змінити критерії пошуку.");
            return "/message";
        }

        }
        return "house/house_list_filter" ;
    }


    private String getString(@Valid HousesFilter housesFilter, BindingResult bindingResult, Model model) {
        Map<String, String> errorsMap = bindingResult.getFieldErrors().stream().collect(
                Collectors.toMap(fieldError -> fieldError.getField() + "Error", FieldError::getDefaultMessage));

        errorsMap.forEach((k,v) -> System.out.println(k+v));

        model.addAttribute("errorMessage", " ");
        model.mergeAttributes(errorsMap);
        model.addAttribute("houseFilter", housesFilter);
        return "index";
    }



}
