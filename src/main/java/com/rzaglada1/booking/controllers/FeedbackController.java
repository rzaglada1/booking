package com.rzaglada1.booking.controllers;


import com.rzaglada1.booking.models.Feedback;
import com.rzaglada1.booking.services.FeedbackService;
import com.rzaglada1.booking.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.security.Principal;

@Controller
@RequestMapping(value = "/feedbacks")
@RequiredArgsConstructor
public class FeedbackController {
    private final FeedbackService feedbackService;
    private final UserService userService;


    // create new house
    @PostMapping("/{idHouse}")
    public String feedback(Feedback feedback, @PathVariable long idHouse, Principal principal) {

            feedbackService.saveToBase(feedback, idHouse, principal);

        return "redirect:/";
    }
}
