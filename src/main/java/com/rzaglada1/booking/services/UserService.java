package com.rzaglada1.booking.services;

import com.rzaglada1.booking.controllers.AuthController;
import com.rzaglada1.booking.models.User;
import com.rzaglada1.booking.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;


    public boolean isEmpty () {
        return userRepository.size() == 0;
    }




    public User getUserByPrincipal(Principal principal) {
        User user = new User();
        if (principal != null) {
            user = userRepository.findByEmail(principal.getName());
        }
        return user;
    }

    public HttpHeaders getHeaders(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.set("Authorization", token);
        return headers;
    }


    public  User getUserByToken(String token, String uri) {
        User user = new User();
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = getHeaders(token);
        HttpEntity<User> userAuth = new HttpEntity<>(headers);
        try {
            ResponseEntity<User> userResponseEntity = restTemplate.exchange(uri, HttpMethod.GET, userAuth, User.class);

            if (userResponseEntity.getStatusCode().is2xxSuccessful() && userAuth != null) {
                user = userResponseEntity.getBody();
            }
        } catch (HttpClientErrorException e) {
            System.out.println("error get param ");
            System.out.println(e.getMessage());
        }
        return user;
    }


    public void userLogout (String token, String uri) {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = getHeaders(token);
        HttpEntity<User> userAuth = new HttpEntity<>(headers);

        try {
            restTemplate.exchange(uri, HttpMethod.GET, userAuth, User.class);
            AuthController.token = null;

        } catch (HttpClientErrorException e) {
            e.printStackTrace();
        }
    }

}
