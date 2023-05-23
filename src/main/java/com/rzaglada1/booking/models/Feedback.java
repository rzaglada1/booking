package com.rzaglada1.booking.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Feedback {

    private long id;

    private String name;
    private String description;

    private double rating;
    private LocalDateTime dateCreate;

    private House house;
    private User user;



    @PrePersist
    private void init() {
        dateCreate = LocalDateTime.now();
    }


    @Override
    public String toString() {
        return "Feedback{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", dateCreate=" + dateCreate +
                '}';
    }
}
