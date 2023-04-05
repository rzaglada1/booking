package com.rzaglada1.booking.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "houses")
public class House {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    private String name;
    private String description;
    private int numTourists;
    private double price;

    private Boolean isAvailable;

    private LocalDate dataBooking;
    private int numDaysBooking;

    private LocalDateTime dateCreate;

    @OneToOne (optional=false, mappedBy="house", cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private Address address;

    @OneToOne (optional=false, mappedBy="house", cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private Image image;

    @OneToMany (mappedBy="house", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Feedback> feedbackList;

    @OneToOne (mappedBy="house", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Wish wish;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User user;

    @OneToMany (mappedBy="house", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
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
                ", dataBooking=" + dataBooking +
                ", numDaysBooking=" + numDaysBooking +
//                ", address=" + address + '\n' +
//                ", feedback=" + feedbackList + '\n' +
                '}' +'\n';
    }
}
