package com.rzaglada1.booking.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class HouseWithoutImage {

    private long id;

    private String name;
    private String description;
    private int numTourists;
    private double price;
    private Boolean isAvailable;
    private String isAvailableForm;
    private LocalDateTime dateCreate;
    private Address address;

}
