package com.rzaglada1.booking;

import com.rzaglada1.booking.models.*;
import com.rzaglada1.booking.models.enams.Role;
import com.rzaglada1.booking.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;

@SpringBootApplication
public class BookingApplication {

    static AddressService addressService;
    static HouseService houseService;
    static UserService userService;
    static FeedbackService feedbackService;

    @Autowired
    public BookingApplication(AddressService addressService, HouseService restaurantService
            , UserService userService, FeedbackService feedbackService
    ) {
        this.addressService = addressService;
        BookingApplication.houseService = restaurantService;
        this.userService = userService;
        this.feedbackService = feedbackService;
    }

    public static void main(String[] args) {
        SpringApplication.run(BookingApplication.class, args);

//		creatingUsers();

//		setupDataBase();
//		setupDataBase();
//		restaurantService.deleteById(1);

//		createFeedback();
//		createFeedback();

        //restaurantService.deleteById(52);
        //feedbackService.deleteById(403);
        //userService.deleteById(54);


        //System.out.println(userService.getById(2).get().getEmail());
        //System.out.println(userService.getAll().get(1).getId());
        //System.out.println(userService.getByIdUser(2));


        //System.out.println(addressService.getById(1));
        //System.out.println(userService.getById(1));

        //restaurantService.getAll().forEach(System.out::println);
        //System.out.println(restaurantService.getById(1).get());
        //restaurantService.getById(52).get().getScheduleList().forEach(System.out::println);
        //System.out.println(tableRestaurantService.getById(152).get().getRestaurant().getName());
        //System.out.println(addressService.getById(102).get().getRestaurant().getName());
        //System.out.println(feedbackService.getById(352).get().getRestaurant().getName());
        //feedbackService.getById(352).get().getUser().getFeedbackList().size();


        //System.out.println(addressService.getById(52));


    }

    static void createFeedback() {
        Feedback feedback = new Feedback();
        feedback.setHouse(houseService.getById(2).get());
        feedback.setName("Feedback about Granit");
        feedback.setDescription("Some description ");
        feedback.setUser(userService.getById(2).get());

        feedbackService.saveToBase(feedback);
    }


    static void creatingUsers() {
        User user1 = new User();
        User user2 = new User();
        User user3 = new User();

        user1.setFirstName("Ruslan");
        user1.setLastName("Zagorov");
        user1.setEmail("Ruslan@google.com");
        user1.setPhone("+380509875656");
        user1.getRoles().add(Role.ROLE_ADMIN);
        user1.setPassword("1");

        user2.setFirstName("Olga");
        user2.setLastName("Vitopend");
        user2.setEmail("Olga@google.com");
        user2.setPhone("+380509873239");
        user2.getRoles().add(Role.ROLE_USER);
        user2.setPassword("1");

        user3.setFirstName("Vitaliy");
        user3.setLastName("Rozhik");
        user3.setEmail("Vitaliy@google.com");
        user3.setPhone("+380509877645");
        user3.getRoles().add(Role.ROLE_GUEST);
        user3.setPassword("1");

        userService.saveToBase(user1);
        userService.saveToBase(user2);
        userService.saveToBase(user3);

    }


    static void setupDataBase() {
        Address address = new Address();
        House house = new House();

        address.setCountry("Turkish");
        address.setCity("Ankara");
        address.setStreet("Shevchenko");
        address.setNumber("3");
        address.setDateCreate(LocalDateTime.now());
        house.setAddress(address);
        address.setHouse(house);

        house.setName("Granit");

//		try {
//			restaurantService.saveToBase(restaurant, address, null);
//		} catch (IOException e) {
//			System.out.println("Something wrong");
//		}
    }

}
