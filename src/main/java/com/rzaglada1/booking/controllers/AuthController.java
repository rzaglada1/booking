package com.rzaglada1.booking.controllers;


import com.rzaglada1.booking.models.RequestAuth;
import com.rzaglada1.booking.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;



@RequiredArgsConstructor
@Controller
public class AuthController {

    public static String token;
    public static String uriUserParam = "http://localhost:8079/users/param";
    private final UserService userService;





    @GetMapping("/auth/login")
    public String login() {
        return "user/login";
    }

    @PostMapping("/auth/login")
    public String authRequest(Model model, RequestAuth requestAuth) {
        String uri = "http://localhost:8079/auth/login";
        ResponseEntity<RequestAuth> responseEntity;

        try {
            RestTemplate restTemplate = new RestTemplate();
            responseEntity = restTemplate.postForEntity(uri, requestAuth, RequestAuth.class);

            if (responseEntity.getStatusCode().is2xxSuccessful() && responseEntity.getBody().getAccessToken() != null) {

                token = "Bearer " + responseEntity.getBody().getAccessToken();
            }
            // token ======================

        } catch (HttpClientErrorException | NullPointerException e) {
            // if error validation
            model.addAttribute("errorMessage", "Не вірний пароль або користувач");
            return "user/login";
        }

        return "redirect:/";
    }

    @GetMapping("/auth/logout")
    public String logout() {
        String uriUserLogout = "http://localhost:8079/auth/logout";
        userService.userLogout(token, uriUserLogout);
        return "redirect:/";
    }


}
