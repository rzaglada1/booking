package com.rzaglada1.booking.controllers;

import com.rzaglada1.booking.models.OrderHistory;
import com.rzaglada1.booking.models.enams.Role;
import com.rzaglada1.booking.services.OrderHistoryService;
import com.rzaglada1.booking.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    public String orders (Model model
            , Principal principal
            ,@PageableDefault(size = 3, page = 0) Pageable pageable) {

        if (userService.getUserByPrincipal(principal) != null) {
            Page<OrderHistory> orderPage = orderHistoryService
                    .findOrdersByUser(userService.getUserByPrincipal(principal), pageable);

            model.addAttribute("user", userService.getUserByPrincipal(principal));
            model.addAttribute("page",orderPage);
            model.addAttribute("url", "/orders");

            if (userService.getUserByPrincipal(principal).getRoles().contains(Role.ROLE_ADMIN)) {
                model.addAttribute("admin", "admin");
            }

            if (orderPage.getTotalElements() == 0) {
                model.addAttribute("message", "Поки нічого немає.");
                return "/message";
            }

        }
        return "orders/orders_history";
    }

}

