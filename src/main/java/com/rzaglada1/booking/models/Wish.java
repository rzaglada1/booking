package com.rzaglada1.booking.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Wish {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

//    private LocalDate wishDateBooking;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private House house;


    private LocalDateTime dateCreate;

    @PrePersist
    private void init () {
        dateCreate = LocalDateTime.now();
    }
}
