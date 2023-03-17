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
@Table(name = "OrderHistories")
public class OrderHistory {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    double price;

    private LocalDateTime dateCreate;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    User user;

    @OneToOne
    House house;

    @PrePersist
    private void init () {
        dateCreate = LocalDateTime.now();
    }


}
