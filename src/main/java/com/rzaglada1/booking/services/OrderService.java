package com.rzaglada1.booking.services;

import com.rzaglada1.booking.repositories.OrderHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private OrderHistoryRepository repository;

}
