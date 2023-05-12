package com.rzaglada1.booking.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rzaglada1.booking.models.House;
import com.rzaglada1.booking.models.HousesFilter;
import com.rzaglada1.booking.models.User;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;



@Controller
@RequiredArgsConstructor
public class MainController {

    private final HouseService houseService;
    private final UserService userService;



    @GetMapping("/")
    public String index(Model model) {

        //check not users
        if (userService.isEmpty()) {
            return "redirect:/users/new";
        }

        if (AuthController.token != null) {
            User user = userService.getUserByToken(AuthController.token, AuthController.uriUserParam);
            if (user != null) {
                model.addAttribute("user", user);
                if (user.getRoles().contains(Role.ROLE_ADMIN)) {
                    model.addAttribute("admin", "admin");
                }
            } else {
                return "redirect:/loginAuth";
            }
        }
        return "index";
    }





    @PostMapping("/find")
    public String houseFind(
            @Valid HousesFilter housesFilter
            , BindingResult bindingResult
            , @RequestParam(value = "country", defaultValue = "%") String country
            , @RequestParam(value = "city", defaultValue = "%") String city
            , @RequestParam(value = "date", defaultValue = "1970-01-01") LocalDate date
            , @RequestParam(value = "days", defaultValue = "1") int days
            , @RequestParam(value = "people", defaultValue = "1") int people
            , Model model
            , Principal principal
            , @PageableDefault(size = 3, page = 0) Pageable pageable
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
            housePage = houseService.filterHouses(country, city, date, days, people, pageable);
            model.addAttribute("page", housePage);
            model.addAttribute("url", "/find?");

            if (housePage.getTotalElements() == 0) {
                model.addAttribute("message", "Нічого не знайдено. Спробуйте змінити критерії пошуку.");
                return "/message";
            }

        }
        return "house/house_list_filter";
    }


    private String getString(@Valid HousesFilter housesFilter, BindingResult bindingResult, Model model) {
        Map<String, String> errorsMap = bindingResult.getFieldErrors().stream().collect(
                Collectors.toMap(fieldError -> fieldError.getField() + "Error", FieldError::getDefaultMessage));

        errorsMap.forEach((k, v) -> System.out.println(k + v));

        model.addAttribute("errorMessage", " ");
        model.mergeAttributes(errorsMap);
        model.addAttribute("houseFilter", housesFilter);
        return "index";
    }

    private Map<String, String> getMapError(String responseError) throws JsonProcessingException {
        String startString = "400 : \"{\"";
        String endString = "\"";
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> mapError = new HashMap<>();

        if (responseError.startsWith(startString)) {
            int startIndex = responseError.indexOf(startString);
            int endIndex = responseError.lastIndexOf(endString);
            String jsonError = responseError.substring(startIndex + startString.length() - 2, endIndex);

            mapError = mapper.readValue(jsonError, Map.class);
        }
        return mapError;
    }

}
