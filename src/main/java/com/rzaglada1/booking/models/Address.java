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
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String country;
    private String city;
    private String street;
    private String number;
    private String apartment;

    private LocalDateTime dateCreate;

    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private House house;

    @PrePersist
    private void init() {
        dateCreate = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", number='" + number + '\'' +
                ", apartment='" + apartment + '\'' +
                ", dateCreate=" + dateCreate +
                '}';
    }
}
