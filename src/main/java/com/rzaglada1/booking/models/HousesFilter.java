package com.rzaglada1.booking.models;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class HousesFilter {


    @Length(max = 50, message = "Довжина не повинна перевищувати 50 символів")
    private String country;
    @Length(max = 50, message = "Довжина не повинна перевищувати 50 символів")
    private String city;
    @FutureOrPresent(message = "Дата менша за поточну")
    private LocalDate date;
    @Min(value = 1, message = "кількість днів від 1")
    @Max( value = 50, message = "кількість днів до 50")
    private Integer days;
    @Min(value = 1, message = "кількість людей від 1")
    @Max( value = 50, message = "кількість людей до 50")
    private Integer people = 2;

}
