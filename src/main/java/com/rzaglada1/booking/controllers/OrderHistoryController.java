package com.rzaglada1.booking.controllers;

import com.rzaglada1.booking.models.enams.Role;
import com.rzaglada1.booking.services.OrderHistoryService;
import com.rzaglada1.booking.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;


@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderHistoryController {
    private final OrderHistoryService orderHistoryService;
    private final UserService userService;

    @GetMapping
    public String orders (Model model, Principal principal) {
        if (userService.getUserByPrincipal(principal) != null) {
            model.addAttribute("user", userService.getUserByPrincipal(principal));
            model.addAttribute("orders", orderHistoryService.findOrdersByUser(userService.getUserByPrincipal(principal)));
            if (userService.getUserByPrincipal(principal).getRoles().contains(Role.ROLE_ADMIN)) {
                model.addAttribute("admin", "admin");
            }
        }
        return "orders/orders_history";
    }

}

