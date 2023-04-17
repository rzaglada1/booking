package com.rzaglada1.booking.repositories;

import com.rzaglada1.booking.models.House;
import com.rzaglada1.booking.models.OrderHistory;
import com.rzaglada1.booking.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderHistoryRepository extends JpaRepository<OrderHistory, Long> {
    List<OrderHistory> findOrderHistoriesByUser(User user);
    List<OrderHistory> findOrderHistoriesByHouse (House house);

}
