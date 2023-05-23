package com.rzaglada1.booking.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class House {

    private long id;

    private String name;
    private String description;
    private int numTourists;
    private double price;

    @JsonIgnore
    private Long imageId;

    private Boolean isAvailable;

    @Transient
    private String isAvailableForm;

    private LocalDateTime dateCreate;

    private Address address;
    private Image image;

    private List<Feedback> feedbackList;

    private List<Wish> wishList;
    private User user;

    private List<OrderHistory> orderHistoryList;


    @PrePersist
    private void init () {
        dateCreate = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "House{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", dateCreate=" + dateCreate +
                ", address=" + address + '\n' +
                ", image=" + image + '\n' +
                ", imageId=" + imageId + '\n' +
                ", user=" + user + '\n' +
                ", feedback=" + feedbackList + '\n' +
                ", wish=" + wishList + '\n' +
                ", orderHistory=" + orderHistoryList + '\n' +
                '}' +'\n';
    }
}
