package com.rzaglada1.booking.repositories;

import com.rzaglada1.booking.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail (String email);

}
