package com.rzaglada1.booking.controllers;

import com.rzaglada1.booking.models.OrderHistory;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.security.Principal;


@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderHistoryController {
    private final UserService userService;
    String uriUserParam = "http://localhost:8079/users/param";

    @GetMapping
    public String orders(
            Model model
            , @PageableDefault(size = 3, page = 0) Pageable pageable) {

        String uriOrders = "http://localhost:8079/orders?page=" + pageable.getPageNumber();


        if (AuthController.token != null) {
            RestTemplate restTemplate = new RestTemplate();
            User userAuth = userService.getUserByToken(AuthController.token, uriUserParam);
            if (userAuth.getEmail() == null) {
                return "redirect:/auth/login";
            }
            HttpHeaders headers = userService.getHeaders(AuthController.token);
            try {
                ParameterizedTypeReference<PaginatedResponse<OrderHistory>> responseType = new ParameterizedTypeReference<>() {
                };
                ResponseEntity<PaginatedResponse<OrderHistory>> result = restTemplate.exchange(
                        uriOrders
                        , HttpMethod.GET
                        , new HttpEntity<>(headers)
                        , responseType
                );

                Page<OrderHistory> ordersPage = result.getBody();

                model.addAttribute("user", userAuth);
                model.addAttribute("page", ordersPage);
                model.addAttribute("url", "/orders");

                if (userAuth.getRoles().contains(Role.ROLE_ADMIN)) {
                    model.addAttribute("admin", "admin");
                }

                if (ordersPage.getTotalElements() == 0) {
                    model.addAttribute("message", "Поки нічого немає.");
                    return "/message";
                }


            } catch (HttpClientErrorException e) {
                e.printStackTrace();
            }

        } else {
            return "redirect:/auth/login";
        }
        return "orders/orders_history";
    }

}

