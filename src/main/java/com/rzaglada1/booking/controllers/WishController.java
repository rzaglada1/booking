package com.rzaglada1.booking.controllers;

import com.rzaglada1.booking.models.User;
import com.rzaglada1.booking.models.Wish;
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

@Controller
@RequestMapping("/wishes")
@RequiredArgsConstructor
public class WishController {
    private final UserService userService;

    @GetMapping()
    public String wishes (Model model
            , @PageableDefault(size = 3, page = 0) Pageable pageable) {

        String uriUserParam = "http://localhost:8079/users/param";
        String uriWishes = "http://localhost:8079/wishes?page=" + pageable.getPageNumber();


        if (AuthController.token != null) {
            RestTemplate restTemplate = new RestTemplate();
            User userAuth = userService.getUserByToken(AuthController.token, uriUserParam);
            HttpHeaders headers = userService.getHeaders(AuthController.token);
            try {
                ParameterizedTypeReference<PaginatedResponse<Wish>> responseType = new ParameterizedTypeReference<>() {
                };
                ResponseEntity<PaginatedResponse<Wish>> result = restTemplate.exchange(
                        uriWishes
                        , HttpMethod.GET
                        , new HttpEntity<>(headers)
                        , responseType
                );

                Page<Wish> wishPage = result.getBody();

                model.addAttribute("page", wishPage);
                model.addAttribute("user", userAuth);
                model.addAttribute("url", "/wishes");

                if (userAuth.getRoles().contains(Role.ROLE_ADMIN)) {
                    model.addAttribute("admin", "admin");
                }

                if (wishPage.getTotalElements() == 0) {
                    model.addAttribute("message", "Поки нічого немає.");
                    return "/message";
                }


            } catch (HttpClientErrorException e) {
                e.printStackTrace();
            }

        } else {
            return "redirect:/redirect:/auth/login";
        }

        return "wishes/wishes_list";
    }





//        if (userService.getUserByPrincipal(principal).getEmail() != null) {
//            Page<Wish> wishPage = wishService.getWishByUser(principal, pageable);
//            model.addAttribute("page", wishPage);
//            model.addAttribute("user", userService.getUserByPrincipal(principal));
//            model.addAttribute("url", "/wishes");
//
//            if (userService.getUserByPrincipal(principal).getRoles().contains(Role.ROLE_ADMIN)) {
//                model.addAttribute("admin", "admin");
//            }
//
//            if (wishPage.getTotalElements() == 0) {
//                model.addAttribute("message", "Поки нічого немає.");
//                return "/message";
//            }
//
//        }
//
//
//        return "wishes/wishes_list";
//    }



    @GetMapping("/{houseId}/delete")
    public String wishes (Model model
            , @PathVariable long houseId
) {


        String uriUserParam = "http://localhost:8079/users/param";
        String uriWishes = "http://localhost:8079/wishes/" + houseId;


        if (AuthController.token != null) {
            RestTemplate restTemplate = new RestTemplate();
            User userAuth = userService.getUserByToken(AuthController.token, uriUserParam);
            HttpHeaders headers = userService.getHeaders(AuthController.token);
            try {
                ParameterizedTypeReference<PaginatedResponse<Wish>> responseType = new ParameterizedTypeReference<>() {
                };
                ResponseEntity<PaginatedResponse<Wish>> result = restTemplate.exchange(
                        uriWishes
                        , HttpMethod.DELETE
                        , new HttpEntity<>(headers)
                        , responseType
                );

//                Page<Wish> wishPage = result.getBody();
//
//                model.addAttribute("page", wishPage);
//                model.addAttribute("user", userAuth);
//                model.addAttribute("url", "/wishes");
//
//                if (userAuth.getRoles().contains(Role.ROLE_ADMIN)) {
//                    model.addAttribute("admin", "admin");
//                }
//
//                if (wishPage.getTotalElements() == 0) {
//                    model.addAttribute("message", "Поки нічого немає.");
//                    return "/message";
//                }


            } catch (HttpClientErrorException e) {
                e.printStackTrace();
            }

        } else {
            return "redirect:/auth/login";
        }

        return "redirect:/wishes";

//
//
//
//
//
//
//
//
//
//        if (userService.getUserByPrincipal(principal) != null) {
//            wishService.deleteFromBase(houseId,principal);
//            model.addAttribute("page", wishService.getWishByUser(principal, pageable) );
//            model.addAttribute("user", userService.getUserByPrincipal(principal));
//            model.addAttribute("url", "/wishes");
//            if (userService.getUserByPrincipal(principal).getRoles().contains(Role.ROLE_ADMIN)) {
//                model.addAttribute("admin", "admin");
//            }
//        }
//        return "wishes/wishes_list";
    }

    @PostMapping("/{idHouse}/new")
    public String houseToWish( @PathVariable long idHouse) {
        String uriWishNew = "http://localhost:8079/wishes/" + idHouse;

        System.out.println("0000000");
        if (AuthController.token != null) {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = userService.getHeaders(AuthController.token);
            HttpEntity<Wish> wishEntity = new HttpEntity<>(new Wish(), headers);

            try {
                System.out.println("1111111");
                restTemplate.exchange(uriWishNew, HttpMethod.POST, wishEntity, Wish.class);
                System.out.println("2222222");
            } catch (HttpClientErrorException e) {
                    System.out.println(e.getMessage());
                }
        } else {
            return "redirect:/redirect:/auth/login";
        }
        return "redirect:/houses/" + idHouse + "/detail";
    }
}
