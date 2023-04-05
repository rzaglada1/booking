package com.rzaglada1.booking.repositories;

import com.rzaglada1.booking.models.Wish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishRepository extends JpaRepository<Wish, Long> {
}
