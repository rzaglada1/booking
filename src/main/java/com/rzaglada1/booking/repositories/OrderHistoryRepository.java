package com.rzaglada1.booking.repositories;

import com.rzaglada1.booking.models.OrderHistory;
import com.rzaglada1.booking.models.OrderHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderHistoryRepository extends JpaRepository<OrderHistory, Long> {
    List<OrderHistory> findOrderHistoriesByUserId(long userId);
}
