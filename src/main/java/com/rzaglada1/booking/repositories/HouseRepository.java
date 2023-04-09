package com.rzaglada1.booking.repositories;

import com.rzaglada1.booking.models.House;
import com.rzaglada1.booking.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HouseRepository extends JpaRepository<House, Long> {
    List<House> getHouseByUser (User user);

}
