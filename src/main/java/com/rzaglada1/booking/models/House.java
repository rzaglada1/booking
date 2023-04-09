package com.rzaglada1.booking.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    @Column(columnDefinition = "text")
    private String description;
    private int numTourists;
    private double price;

    private Boolean isAvailable;
    @Transient
    private String isAvailableForm;



    private LocalDateTime dateCreate;

    @OneToOne (optional=false, mappedBy="house", cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private Address address;

    @OneToOne (optional=false, mappedBy="house", cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private Image image;

    @OneToMany (mappedBy="house", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Feedback> feedbackList;

    @OneToMany (mappedBy="house", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Wish> wishList;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User user;

    @OneToMany (mappedBy="house", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderHistory> orderHistoryList;


    public double getAverRating() {
        return feedbackList.stream()
                .mapToDouble(Feedback::getRating)
                .average().orElse(-1);
    }

    public int getCountFeedback() {
        return feedbackList.size();
    }

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
//                ", address=" + address + '\n' +
//                ", feedback=" + feedbackList + '\n' +
                '}' +'\n';
    }
}
