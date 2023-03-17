package com.rzaglada1.booking.services;

import com.rzaglada1.booking.models.Feedback;
import com.rzaglada1.booking.repositories.FeedbackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FeedbackService {
    private final FeedbackRepository repository;


    public void saveToBase (Feedback Feedback) {
        repository.save(Feedback);
    }

    public void deleteById (long id) {
        repository.delete(repository.getReferenceById(id));
    }

    public List<Feedback> getAll () {
        return repository.findAll();
    }

    public Optional<Feedback> getById (long id) {
        return repository.findById(id);
    }

}
