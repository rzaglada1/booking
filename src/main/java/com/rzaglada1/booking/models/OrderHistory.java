package com.rzaglada1.booking.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "OrderHistories")
public class OrderHistory {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    private double price;

    private LocalDate dataBooking;
    private int numDaysBooking;
    private int numTourists;


    private LocalDateTime dateCreate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private House house;

    @PrePersist
    private void init () {
        dateCreate = LocalDateTime.now();
    }


}
