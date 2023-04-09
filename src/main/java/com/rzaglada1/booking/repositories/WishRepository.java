package com.rzaglada1.booking.repositories;

import com.rzaglada1.booking.models.User;
import com.rzaglada1.booking.models.Wish;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface WishRepository extends JpaRepository<Wish, Long> {
    List<Wish> getWishByUser (User user);
    boolean existsByHouseIdAndUser (long idHouse, User user);
    Wish getWishByHouseIdAndUser (long houseId, User user);
}
