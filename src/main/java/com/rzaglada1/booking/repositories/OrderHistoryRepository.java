package com.rzaglada1.booking.repositories;

import com.rzaglada1.booking.models.House;
import com.rzaglada1.booking.models.OrderHistory;
import com.rzaglada1.booking.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderHistoryRepository extends JpaRepository<OrderHistory, Long> {
    Page<OrderHistory> findOrderHistoriesByUser(User user, Pageable pageable);
    List<OrderHistory> findOrderHistoriesByHouse (House house);

}
