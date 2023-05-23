package com.rzaglada1.booking.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class HousesFilter {


    private String country;
    private String city;
    private LocalDate date ;
    private Integer days;
    private Integer people;

}
