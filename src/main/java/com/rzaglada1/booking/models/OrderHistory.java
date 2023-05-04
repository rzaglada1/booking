package com.rzaglada1.booking.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

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

    @NotNull(message = "Введіть дату початку")
    @FutureOrPresent(message = "Дата менша за поточну")
    private LocalDate dataBookingStart ;
    private LocalDate dataBookingEnd ;
    @Min(value = 1, message = "кількість днів від 1")
    @Max( value = 50, message = "кількість днів до 50")
    private long numDaysBooking;
    @Min(value = 1, message = "кількість людей від 1")
    @Max( value = 50, message = "кількість людей до 50")
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

    public void setHouse(House house) {
        this.house = house;
        this.numDaysBooking = dataBookingStart.until(dataBookingEnd, ChronoUnit.DAYS);
        this.price = house.getPrice() * this.numDaysBooking;
    }
}
