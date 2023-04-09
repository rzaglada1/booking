package com.rzaglada1.booking.controllers;

import com.rzaglada1.booking.models.Wish;
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

    @GetMapping()
    public String wishes (Model model, Principal principal) {
        model.addAttribute("wishes", wishService.getWishByUser(principal));

        return "wishes/wishes_list";
    }
    @GetMapping("/{houseId}/delete")
    public String wishes (Model model, @PathVariable long houseId, Principal principal) {
        wishService.deleteFromBase(houseId,principal);
        model.addAttribute("wishes", wishService.getWishByUser(principal));

        return "wishes/wishes_list";
    }

    @PostMapping("/{idHouse}/new")
    public String houseToWish(Wish wish, @PathVariable long idHouse, Principal principal) {
        wishService.saveToBase(wish, idHouse, principal);

       // return "redirect:/";
        return "redirect:/houses/" + idHouse + "/detail";
    }
}
