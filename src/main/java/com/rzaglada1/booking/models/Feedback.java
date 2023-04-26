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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "feedbacks")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    @Column(columnDefinition = "text")
    @NotBlank(message = "Напишіть свій відгук")
    @Length(max =2000, message = "Довжина не повинна перевищувати 2000 символів")
    private String description;

    @NotNull(message = "Це поле не повинно бути порожнім")
    @Min(value = 1, message = "оцінка від 1 до 10")
    @Max( value = 10, message = "оцінка від 1 до 10")
    private double rating;
    private LocalDateTime dateCreate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private House house;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
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
