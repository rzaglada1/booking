package com.rzaglada1.booking.repositories;

import com.rzaglada1.booking.models.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
}
