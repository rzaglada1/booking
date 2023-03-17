package com.rzaglada1.booking.repositories;

import com.rzaglada1.booking.models.House;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseRepository extends JpaRepository<House, Long> {
}
