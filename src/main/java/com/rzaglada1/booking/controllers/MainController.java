package com.rzaglada1.booking.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rzaglada1.booking.models.House;
import com.rzaglada1.booking.models.HousesFilter;
import com.rzaglada1.booking.models.User;
import com.rzaglada1.booking.models.enams.Role;
import com.rzaglada1.booking.services.PaginatedResponse;
import com.rzaglada1.booking.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;



@Controller
@RequiredArgsConstructor
public class MainController {

    private final UserService userService;
    private HousesFilter housesFilterRemember;




    @GetMapping("/")
    public String index(Model model) {

        housesFilterRemember = null;
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
              HousesFilter housesFilter
            , Model model
            , @PageableDefault(size = 3) Pageable pageable
    ) throws JsonProcessingException {

        if (housesFilterRemember == null) {
            housesFilterRemember = new HousesFilter();
            housesFilterRemember.setCountry(housesFilter.getCountry());
            housesFilterRemember.setCity(housesFilter.getCity());
            housesFilterRemember.setDate(housesFilter.getDate());
            housesFilterRemember.setDays(housesFilter.getDays());
            housesFilterRemember.setPeople(housesFilter.getPeople());
        }

        String uriUserParam = "http://localhost:8079/users/param";
        String uriHouses = "http://localhost:8079/find?page=" + pageable.getPageNumber();

        if (AuthController.token != null) {
            User userAuth = userService.getUserByToken(AuthController.token, uriUserParam);
            setModelAdmin(model, userAuth);
        }

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = userService.getHeaders(AuthController.token);
        HttpEntity<HousesFilter> httpEntity = new HttpEntity<>(housesFilterRemember, headers);


        try {
            ParameterizedTypeReference<PaginatedResponse<House>> responseType = new ParameterizedTypeReference<>() {
            };
            ResponseEntity<PaginatedResponse<House>> result = restTemplate.exchange(
                    uriHouses
                    , HttpMethod.POST
                    , httpEntity
                    , responseType
            );

            Page<House> housePage = result.getBody();

            model.addAttribute("houses", housePage);
            model.addAttribute("page", housePage);
            model.addAttribute("url", "/find?");

            if (housePage.getTotalElements() == 0) {
                model.addAttribute("message", "Нічого не знайдено. Спробуйте змінити критерії пошуку.");
                return "/message";
            }

        } catch (HttpClientErrorException e) {
            Map<String, String> errorsMap = getMapError(e.getMessage());
            model.addAttribute("errorMessage", " ");
            model.mergeAttributes(errorsMap);
            model.addAttribute("houseFilter", housesFilter);
            housesFilterRemember = null;
            return "index";
        }
        return "house/house_list_filter";
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

    private void setModelAdmin(Model model, User authUser) {
        if (authUser.getRoles().contains(Role.ROLE_ADMIN)) {
            model.addAttribute("admin", "admin");
        }
    }




}
