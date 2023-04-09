package com.rzaglada1.booking.services;

import com.rzaglada1.booking.models.Feedback;
import com.rzaglada1.booking.models.User;
import com.rzaglada1.booking.repositories.FeedbackRepository;
import com.rzaglada1.booking.repositories.HouseRepository;
import com.rzaglada1.booking.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.security.Principal;


@Service
@RequiredArgsConstructor
public class FeedbackService {
    private final FeedbackRepository repository;
    private final UserRepository userRepository;
    private final HouseRepository houseRepository;

    public void saveToBase(Feedback feedback, long idHouse, Principal principal) {
        if (houseRepository.findById(idHouse).isPresent()) {
            feedback.setHouse(houseRepository.findById(idHouse).get());
            feedback.setUser(getUserByPrincipal(principal));
            repository.save(feedback);
        }

    }

    private User getUserByPrincipal(Principal principal) {
        return userRepository.findByEmail(principal.getName());
    }

}


