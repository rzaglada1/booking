package com.rzaglada1.booking.controllers;

import com.rzaglada1.booking.models.Wish;
import com.rzaglada1.booking.models.enams.Role;
import com.rzaglada1.booking.services.UserService;
import com.rzaglada1.booking.services.WishService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    public String wishes (Model model
            , Principal principal
            , @PageableDefault(size = 3, page = 0) Pageable pageable) {
        if (userService.getUserByPrincipal(principal).getEmail() != null) {
            Page<Wish> wishPage = wishService.getWishByUser(principal, pageable);
            model.addAttribute("page", wishPage);
            model.addAttribute("user", userService.getUserByPrincipal(principal));
            model.addAttribute("url", "/wishes");

            if (userService.getUserByPrincipal(principal).getRoles().contains(Role.ROLE_ADMIN)) {
                model.addAttribute("admin", "admin");
            }

            if (wishPage.getTotalElements() == 0) {
                model.addAttribute("message", "Поки нічого немає.");
                return "/message";
            }

        }


        return "wishes/wishes_list";
    }
    @GetMapping("/{houseId}/delete")
    public String wishes (Model model
            , @PathVariable long houseId
            , Principal principal
            , @PageableDefault(size = 3, page = 0) Pageable pageable) {
        if (userService.getUserByPrincipal(principal) != null) {
            wishService.deleteFromBase(houseId,principal);
            model.addAttribute("page", wishService.getWishByUser(principal, pageable) );
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
