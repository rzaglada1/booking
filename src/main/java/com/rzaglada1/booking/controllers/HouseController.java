package com.rzaglada1.booking.controllers;


import com.rzaglada1.booking.models.*;
import com.rzaglada1.booking.models.enams.Role;
import com.rzaglada1.booking.services.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.Map;
import java.util.stream.Collectors;


@Controller
@RequestMapping(value = "/houses")
@RequiredArgsConstructor
public class HouseController {
    private final HouseService houseService;
    private final UserService userService;
    private final WishService wishService;
    private final OrderHistoryService orderHistoryService;
    private final FeedbackService feedbackService;


    // request form all  house

    @GetMapping
    public String houseAll(
              Model model
            , Principal principal
            , @PageableDefault( sort = {"id"}, direction = Sort.Direction.ASC, size = 3) Pageable pageable) {

        Page<House> housePage;
        if (principal != null) {
            if( userService.getUserByPrincipal(principal).getRoles().contains(Role.ROLE_ADMIN)) {
                housePage = houseService.getAll(pageable);
                model.addAttribute("admin", "admin");
            } else {
                housePage = houseService.getHouseByUser(userService.getUserByPrincipal(principal), pageable);
            }
            model.addAttribute("houses", housePage);
            model.addAttribute("page", housePage);
            model.addAttribute("url", "/houses");

            model.addAttribute("user", userService.getUserByPrincipal(principal));
            if (housePage.getTotalElements() == 0) {
                model.addAttribute("message", "Поки нічого не має.");
                return "/message";
            }
        }
        return "house/house_list";
    }


    // request form new  house
    @GetMapping("/new")
    public String houseNew(Model model, Principal principal) {
        model.addAttribute("house", null);
        model.addAttribute("address", new Address());
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        setModelAdmin(model,principal);
        return "house/house_form";
    }


    // create new house
    @PostMapping
    public String house(
              @RequestParam(value = "file", required = false) MultipartFile file
            , @Valid House house
            , BindingResult bindingResultHouse
            , @Valid Address address
            , BindingResult bindingResultAddress
            , Model model
            , Principal principal
    ) {
        model.addAttribute("new", " ");
        //check is active
        checkActive(house);
        // check validError
        if (bindingResultHouse.hasErrors()  || bindingResultAddress.hasErrors()) {
            setModelError(model, house, address, bindingResultHouse, bindingResultAddress);
            return "house/house_form";
        } else {
            try {
                houseService.saveToBase(principal, house, address, file);
            } catch (IOException e) {
                System.out.println("Something wrong");
            }
            return "redirect:/houses";
        }
    }


    // update house
    @PostMapping("/{id}")
    public String houseUpdate(
            @RequestParam(value = "file", required = false) MultipartFile file
            , @Valid House house
            , BindingResult bindingResultHouse
            , @Valid Address address
            , BindingResult bindingResultAddress
            , Model model
            , @PathVariable long id
            , Principal principal
    ) {
        model.addAttribute("edit", " ");
        //check is active
        checkActive(house);

        //check validError
        if (bindingResultHouse.hasErrors()  || bindingResultAddress.hasErrors()) {
            setModelError(model, house, address, bindingResultHouse, bindingResultAddress);
            return "house/house_form";
        } else {
            try {
                //check is active
                checkActive(house);
                if (principal != null && houseService.getById(id).isPresent()) {
                    if (userService.getUserByPrincipal(principal).getRoles().contains(Role.ROLE_ADMIN)) {
                        houseService.update(house, address, file, id);
                    }
                    if (houseService.getHouseById(id).orElseThrow().getUser().equals(userService.getUserByPrincipal(principal))) {
                        houseService.update(house, address, file, id);
                    }
                }
            } catch (IOException e) {
                System.out.println("Something wrong");
            }
            return "redirect:/houses";
        }
    }

    // request form edit house by id
    @GetMapping("/{id}/edit")
    public String houseEdit(@PathVariable Long id, Model model, Principal principal) {

        model.addAttribute("edit", " ");
        if (principal != null && houseService.getById(id).isPresent()) {
            if (userService.getUserByPrincipal(principal).getRoles().contains(Role.ROLE_ADMIN)
                    || houseService.getHouseById(id).orElseThrow().getUser().equals(userService.getUserByPrincipal(principal))
            ) {
                model.addAttribute("house", houseService.getById(id).get());
                model.addAttribute("address", houseService.getById(id).get().getAddress());
                model.addAttribute("user", userService.getUserByPrincipal(principal));
            }
            setModelAdmin(model,principal);
        }
        return "house/house_form";
    }

    @GetMapping("/{id}/delete")
    public String houseDelete(@PathVariable("id") Long id, Principal principal) {
        if (principal != null && houseService.getById(id).isPresent()) {
            if (userService.getUserByPrincipal(principal).getRoles().contains(Role.ROLE_ADMIN)
                    || houseService.getHouseById(id).orElseThrow().getUser().equals(userService.getUserByPrincipal(principal))
            ) {
                houseService.deleteById(id);
            }
        }

        return "redirect:/houses";
    }





    @GetMapping("/{houseId}/detail")
    public String houseDetail(@PathVariable("houseId") Long houseId, Model model, Principal principal) {
        houseModel(model, principal, houseId);
        return "house/house_detail";
    }


    @PostMapping("/{houseId}/feedbacks")
    public String houseDetailFeedback(
              @PathVariable("houseId") Long houseId
            , @Valid Feedback feedback
            , BindingResult bindingResult
            , Model model
            , Principal principal
    ) {
        houseModel(model, principal, houseId);


        if (bindingResult.hasErrors() ) {
            Map<String, String> errorsMapHouse = mapErrors(bindingResult);
            model.mergeAttributes(errorsMapHouse);
            return "house/house_detail";
        }
        feedbackService.saveToBase(feedback, houseId, principal);
        return "house/house_detail";
    }


    @PostMapping("/{houseId}/prebooking")
    public String hosePreBooking(
            @PathVariable("houseId") long houseId
            ,@Valid OrderHistory orderHistory
            , BindingResult bindingResult
            , Model model
            , Principal principal) {

        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = mapErrors(bindingResult);
            model.addAttribute("errorMessage", " ");
            model.mergeAttributes(errorsMap);
            model.addAttribute("orderHistory", orderHistory);
            houseModel(model, principal, houseId);
            return "house/house_detail";
        } else {
            orderHistory.setDataBookingEnd(orderHistory.getDataBookingStart().plusDays(orderHistory.getNumDaysBooking()));
            House house = houseService.getHouseById(houseId).orElseThrow();
            if (houseService.isDateFree(orderHistory, houseId) && house.getNumTourists() >= orderHistory.getNumTourists()) {
                orderHistory.setHouse(house);
                model.addAttribute("house", house);
                model.addAttribute("order", orderHistory);

                return "/house/house_booking";
            } else {
                String messageUrl = "/houses/" + houseId +"/detail";
                if (house.getNumTourists() < orderHistory.getNumTourists()) {
                    model.addAttribute("message", "Будинок не розрахований на таку кількість людей");
                } else {
                    model.addAttribute("message", "На вказаний період вже заброньовано. Спробуйте інші дати");
                }
                model.addAttribute("messageUrl", messageUrl );
                return "/message";
            }

        }
    }



    @PostMapping("/{houseId}/booking")
    public String hoseBooking(
            @PathVariable("houseId") long houseId,
            OrderHistory orderHistory,
            Model model,
            Principal principal) {

        if (houseService.getHouseById(houseId).isPresent() && principal != null) {
            House house = houseService.getHouseById(houseId).get();
            orderHistory.setHouse(house);
            orderHistoryService.saveToBase(orderHistory, houseId, principal);
            model.addAttribute("message", "Заброньовано");
        } else {
            model.addAttribute("message", "Щось пішло не так. Спробуйте знову.");
        }

        return "/message";

    }


    private Map<String, String> mapErrors (BindingResult bindingResult) {
        return bindingResult.getFieldErrors().stream().collect(
                Collectors.toMap(fieldError -> fieldError.getField() + "Error", FieldError::getDefaultMessage));
    }


    private void setModelError( Model model, House house, Address address,
                                BindingResult bindingResultHouse, BindingResult bindingResultAddress) {
        Map<String, String> errorsMapHouse = mapErrors(bindingResultHouse);
        Map<String, String> errorsMapAddress = mapErrors(bindingResultAddress);
        model.addAttribute("errorMessage", " ");
        model.mergeAttributes(errorsMapHouse);
        model.mergeAttributes(errorsMapAddress);
        model.addAttribute("house", house);
        model.addAttribute("address", address);
    }

    private void houseModel (Model model, Principal principal, long houseId) {
        if (houseService.getById(houseId).isPresent()) {
            House house = houseService.getById(houseId).get();
            if (!orderHistoryService.findOrdersByHouseForFree(house).isEmpty()) {
                model.addAttribute("orders", orderHistoryService.findOrdersByHouseForFree(house));
            }
            model.addAttribute("house", house);
            model.addAttribute("feedbacks", house.getFeedbackList());

            if (principal != null) {
                if (userService.getUserByPrincipal(principal).getRoles().contains(Role.ROLE_ADMIN)) {
                    model.addAttribute("admin", "admin");
                }
                model.addAttribute("user", userService.getUserByPrincipal(principal));
                model.addAttribute("isWishList", wishService.existsByHouseIdAndUser(houseId, principal));
            }
        }
    }

    private void setModelAdmin (Model model, Principal principal) {
        if (userService.getUserByPrincipal(principal).getRoles().contains(Role.ROLE_ADMIN)) {
            model.addAttribute("admin", "admin");
        }
    }

    private void checkActive (House house) {
        house.setIsAvailable(house.getIsAvailableForm() != null);
    }

}
