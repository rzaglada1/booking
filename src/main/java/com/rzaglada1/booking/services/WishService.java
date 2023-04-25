package com.rzaglada1.booking.services;

import com.rzaglada1.booking.models.User;
import com.rzaglada1.booking.models.Wish;
import com.rzaglada1.booking.repositories.HouseRepository;
import com.rzaglada1.booking.repositories.UserRepository;
import com.rzaglada1.booking.repositories.WishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WishService {
    private final WishRepository wishRepository;
    private final UserRepository userRepository;
    private final HouseRepository houseRepository;

    public Page<Wish> getWishByUser (Principal principal, Pageable pageable) {
        return wishRepository.getWishByUser(getUserByPrincipal(principal), pageable);
    }

    public void saveToBase (Wish wish, long houseId, Principal principal) {

        if (!wishRepository.existsByHouseIdAndUser(houseId, getUserByPrincipal(principal))) {
            wish.setHouse(houseRepository.findById(houseId).get());
            wish.setUser(getUserByPrincipal(principal));
            wishRepository.save(wish);
        }
    }

    public void deleteFromBase (long houseId, Principal principal){
        if (wishRepository.existsByHouseIdAndUser(houseId, getUserByPrincipal(principal) )) {
            wishRepository.delete(wishRepository.getWishByHouseIdAndUser(houseId, getUserByPrincipal(principal)));
        }
    }

    public boolean existsByHouseIdAndUser(long houseId, Principal principal){
        return wishRepository.existsByHouseIdAndUser(houseId, getUserByPrincipal(principal));
    }



    private User getUserByPrincipal(Principal principal) {
        return userRepository.findByEmail(principal.getName());
    }


}
