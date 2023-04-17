package com.rzaglada1.booking.controllers;

import com.rzaglada1.booking.models.Wish;
import com.rzaglada1.booking.models.enams.Role;
import com.rzaglada1.booking.services.UserService;
import com.rzaglada1.booking.services.WishService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/wishes")
@RequiredArgsConstructor
public class WishController {
    private final WishService wishService;
    private final UserService userService;

    @GetMapping()
    public String wishes (Model model, Principal principal) {
        if (userService.getUserByPrincipal(principal).getEmail() != null) {
            model.addAttribute("wishes", wishService.getWishByUser(principal));
            model.addAttribute("user", userService.getUserByPrincipal(principal));
            if (userService.getUserByPrincipal(principal).getRoles().contains(Role.ROLE_ADMIN)) {
                model.addAttribute("admin", "admin");
            }
        }


        return "wishes/wishes_list";
    }
    @GetMapping("/{houseId}/delete")
    public String wishes (Model model, @PathVariable long houseId, Principal principal) {
        if (userService.getUserByPrincipal(principal) != null) {
            wishService.deleteFromBase(houseId,principal);
            model.addAttribute("wishes", wishService.getWishByUser(principal));
            model.addAttribute("user", userService.getUserByPrincipal(principal));
            if (userService.getUserByPrincipal(principal).getRoles().contains(Role.ROLE_ADMIN)) {
                model.addAttribute("admin", "admin");
            }
        }
        return "wishes/wishes_list";
    }

    @PostMapping("/{idHouse}/new")
    public String houseToWish(Wish wish, @PathVariable long idHouse, Principal principal) {
        if (userService.getUserByPrincipal(principal) != null) {
            wishService.saveToBase(wish, idHouse, principal);
        }
       // return "redirect:/";
        return "redirect:/houses/" + idHouse + "/detail";
    }
}
