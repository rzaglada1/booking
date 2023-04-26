package com.rzaglada1.booking.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

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

    @NotBlank(message = "Це поле не повинно бути порожнім11")
    @Length(max = 50, message = "Довжина не повинна перевищувати 50 символів")
    private String country;
    @NotBlank(message = "Це поле не повинно бути порожнім22")
    @Length(max = 50, message = "Довжина не повинна перевищувати 50 символів")
    private String city;
    @NotBlank(message = "Це поле не повинно бути порожнім33")
    @Length(max = 50, message = "Довжина не повинна перевищувати 50 символів")
    private String street;
    @NotBlank(message = "Це поле не повинно бути порожнім44")
    @Length(max = 50, message = "Довжина не повинна перевищувати 50 символів")
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
