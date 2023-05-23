package com.rzaglada1.booking.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Wish {

    private long id;

    private User user;

    private House house;


    private LocalDateTime dateCreate;

    @PrePersist
    private void init () {
        dateCreate = LocalDateTime.now();
    }
}
