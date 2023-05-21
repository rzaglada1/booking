package com.rzaglada1.booking.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping(value = "/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    // request form new  user
    @GetMapping("/new")
    public String userNew(Model model) {
        model.addAttribute("new", " ");
        model.addAttribute("user", null);
        return "user/user_form";
    }

    // create new User
    @PostMapping
    public String userNew(
            User user
            , Model model
    ) {
        user.setActive(true);

        model.addAttribute("new", " ");
        String uri = "http://localhost:8079/users/new";

        RestTemplate restTemplate = new RestTemplate();
        if (!user.getPassword().equals(user.getPasswordConfirm())) {
            model.addAttribute("errorMessage", " ");
            model.addAttribute("errorMessagePass", "passwords do not match");
            return "user/user_form";
        }

        try {
            restTemplate.postForEntity(uri, user, User.class);
        } catch (HttpClientErrorException e) {
            // if error validation
            try {
                Map<String, String> errorsMap = getMapError(e.getMessage());
                model.addAttribute("errorMessage", " ");
                model.mergeAttributes(errorsMap);
                model.addAttribute("user", user);
                return "user/user_form";
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return "redirect:/";
    }


    // update user
    @PostMapping(value = {"/update", "/update/{id}"})
    public String userUpdate(
              User user
            , Model model
            , @PathVariable(required = false) String id
    ) {
        Set<Role> roles = new HashSet<>();
        model.addAttribute("edit", " ");

        String uriUserParam = "http://localhost:8079/users/param";
        String uriUpdate = "http://localhost:8079/users/update";

        if (AuthController.token != null) {
            User userAuth = userService.getUserByToken(AuthController.token, uriUserParam);

            if (userAuth.getRoles().contains(Role.ROLE_ADMIN) && id == null) {
                roles.add(Role.ROLE_ADMIN);
                user.setRoles(roles);
                model.addAttribute("admin", "admin");
            } else if (id == null) {
                roles.add(Role.ROLE_USER);
                user.setRoles(roles);
            }
            //check is active
            if (userAuth.getActive()) {
                user.setActive(true);
            } else {
                user.setActive(false);
            }

            if (!user.getPassword().equals(user.getPasswordConfirm())) {
                model.addAttribute("errorMessage", " ");
                model.addAttribute("errorMessagePass", "passwords do not match");
                return "user/user_form";
            }

            if (userAuth.getRoles().contains(Role.ROLE_ADMIN) && id != null) {
                uriUpdate = "http://localhost:8079/users/update/" + id;
                model.addAttribute("edit", " ");
                model.addAttribute("idPresent", " ");
                model.addAttribute("rolesEnum", Arrays.asList(Role.values()));
                //check is active
                if (user.getActiveForm() != null) {
                    user.setActive(true);
                } else {
                    user.setActive(false);
                }
                // check role
                roles.add(user.getRoleForm());
                user.setRoles(roles);
            }

            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = userService.getHeaders(AuthController.token);
            HttpEntity<User> userEntity = new HttpEntity<>(user, headers);

            try {
                restTemplate.exchange(uriUpdate, HttpMethod.PUT, userEntity, User.class);
            } catch (HttpClientErrorException e) {
                // if error validation
                try {
                    Map<String, String> errorsMap = getMapError(e.getMessage());
                    model.addAttribute("errorMessage", " ");
                    model.mergeAttributes(errorsMap);
                    model.addAttribute("user", user);
                    return "user/user_form";
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } else {
            return "redirect:/redirect:/auth/login";
        }
        return "redirect:/";
    }


    // request form edit user by id
    @GetMapping(value = {"/edit", "/edit/{id}"})
    public String userEdit(Model model, @PathVariable(required = false) String id) {

        String uriUserParam = "http://localhost:8079/users/param";
        if (AuthController.token != null) {
            User user;
            User userAuth = userService.getUserByToken(AuthController.token, uriUserParam);
            model.addAttribute("edit", " ");

            if (userAuth.getRoles().contains(Role.ROLE_ADMIN) && id != null) {
                long userId = Long.parseLong(id);
                String uri = "http://localhost:8079/users/" + userId;
                user = userService.getUserByToken(AuthController.token, uri);

                model.addAttribute("rolesEnum", Arrays.asList(Role.values()));
                model.addAttribute("admin", "admin");
                model.addAttribute("idPresent", " ");
            } else {
                user = userAuth;
            }
            user.setPasswordConfirm(user.getPassword());
            model.addAttribute("user", user);
        }
        return "/user/user_form";
    }

    @GetMapping(value = {"/delete", "/delete/{id}"})
    public String userDelete(@PathVariable(required = false) String id) {

        String uriUserParam = "http://localhost:8079/users/param";
        String uriUserDelete = "http://localhost:8079/users/delete";
        String uriUserLogout = "http://localhost:8079/auth/logout";

        if (AuthController.token != null) {
            User userAuth = userService.getUserByToken(AuthController.token, uriUserParam);

            if (userAuth.getRoles().contains(Role.ROLE_ADMIN) && id != null) {
                uriUserDelete = "http://localhost:8079/users/delete/" + id;
            }

            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = userService.getHeaders(AuthController.token);
            HttpEntity<User> userEntity = new HttpEntity<>(headers);
            try {
                restTemplate.exchange(uriUserDelete, HttpMethod.DELETE, userEntity, User.class);
            } catch (HttpClientErrorException e) {
                System.out.println(e.getMessage());
            }
        } else {
            return "redirect:/redirect:/auth/login";
        }
        userService.userLogout(AuthController.token, uriUserLogout);
        return "redirect:/";
    }



    @GetMapping
    public String userList(Model model, @PageableDefault(size = 3) Pageable pageable) {

        String uriUserParam = "http://localhost:8079/users/param";
        String uriUsers = "http://localhost:8079/users?page=" + pageable.getPageNumber();

        if (AuthController.token != null) {
            User userAuth = userService.getUserByToken(AuthController.token, uriUserParam);

            if (userAuth.getRoles().contains(Role.ROLE_ADMIN)) {
                RestTemplate restTemplate = new RestTemplate();
                HttpHeaders headers = userService.getHeaders(AuthController.token);
                try {
                    ParameterizedTypeReference<PaginatedResponse<User>> responseType = new ParameterizedTypeReference<>() {
                    };
                    ResponseEntity<PaginatedResponse<User>> result = restTemplate.exchange(
                            uriUsers
                            , HttpMethod.GET
                            , new HttpEntity<>(headers)
                            , responseType
                    );

                    Page<User> userPage = result.getBody();

                    model.addAttribute("page", userPage);
                    model.addAttribute("url", "/users");
                    model.addAttribute("admin", "admin");
                    model.addAttribute("user", userAuth);

                } catch (HttpClientErrorException e) {
                    e.printStackTrace();
                }
            }
        } else {
            return "redirect:/redirect:/auth/login";
        }
        return "user/user_list";
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

