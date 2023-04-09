package com.rzaglada1.booking.controllers;

import com.rzaglada1.booking.services.OrderHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderHistoryController {
    private final OrderHistoryService orderHistoryService;

    @GetMapping("/{idUser}")
    public String orders (Model model, @PathVariable long idUser) {
        model.addAttribute("orders", orderHistoryService.findByUserId(idUser));
        return "orders/orders_history";
    }
}
