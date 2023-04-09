package com.rzaglada1.booking.services;

import com.rzaglada1.booking.models.OrderHistory;
import com.rzaglada1.booking.repositories.OrderHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderHistoryService {
    private final OrderHistoryRepository repository;

    public void saveToBase(OrderHistory orderHistory) {
        repository.save(orderHistory);
    }

    public List<OrderHistory> findByUserId (long id) {
        return repository.findOrderHistoriesByUserId(id);
    }
}
