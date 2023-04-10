package com.rzaglada1.booking.repositories;

import com.rzaglada1.booking.models.Address;
import com.rzaglada1.booking.models.House;
import com.rzaglada1.booking.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HouseRepository extends JpaRepository<House, Long> {
    List<House> getHouseByUser (User user);

    @Query("SELECT u.house FROM Address u WHERE u.country = :country and u.city = :city")
    List<House> getHouseByFilter (@Param("country") String country, @Param("city") String city);




}
