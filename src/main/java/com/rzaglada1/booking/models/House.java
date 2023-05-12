package com.rzaglada1.booking.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

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

    @NotBlank(message = "Це поле не повинно бути порожнім")
    @Length(max = 50, message = "Довжина не повинна перевищувати 50 символів")
    private String name;
    @Column(columnDefinition = "text")
    @NotBlank(message = "Це поле не повинно бути порожнім")
    @Length(max = 1000, message = "Довжина не повинна перевищувати 1000 символів")
    private String description;
    @NotNull(message = "Це поле не повинно бути порожнім")
    @Min(value = 1, message = "кількість від 1")
    @Max( value = 50, message = "кількість до 50")
    private int numTourists;
//    @NotBlank(message = "Це поле не повинно бути порожнім")
@Min(value = 0, message = "ціна від 0")
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
