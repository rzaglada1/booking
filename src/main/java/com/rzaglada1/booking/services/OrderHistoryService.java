package com.rzaglada1.booking.services;

import com.rzaglada1.booking.models.House;
import com.rzaglada1.booking.models.OrderHistory;
import com.rzaglada1.booking.models.User;
import com.rzaglada1.booking.repositories.OrderHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderHistoryService {
    private final OrderHistoryRepository orderHistoryRepository;
    private final UserService userService;
    private final HouseService houseService;


    public void saveToBase(OrderHistory orderHistory, long houseId, Principal principal) {
        if (houseService.getById(houseId).isPresent() && principal.getName() !=null) {
            User user = userService.getUserByPrincipal(principal);
            House house = houseService.getById(houseId).get();
            orderHistory.setUser(user);
            orderHistory.setHouse(house);
            orderHistoryRepository.save(orderHistory);
        }
    }

    public List<OrderHistory> findOrdersByHouseForFree (House house) {
        return orderHistoryRepository.findOrderHistoriesByHouse(house).stream()
                .filter (e->e.getDataBookingEnd().isAfter(LocalDate.now() )
                        || e.getDataBookingEnd().equals(LocalDate.now()))
                .sorted(Comparator.comparing(OrderHistory::getDataBookingStart))
                .toList();
    }

    public Page<OrderHistory> findOrdersByUser(User user, Pageable pageable) {
        return orderHistoryRepository.findOrderHistoriesByUser(user, pageable);
        }
}
