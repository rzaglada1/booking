package com.rzaglada1.booking.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rzaglada1.booking.models.enams.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;


    @Column(unique = true)
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private Boolean active;

    @Column(name = "password", length = 1000)
    private String password;

    @Transient
    private String passwordConfirm;
    @Transient
    private String passwordOld;
    @Transient
    private Role roleForm;
    @Transient
    private String activeForm;



    private LocalDateTime dateCreate;

    @OneToMany ( mappedBy="user", cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<OrderHistory> orderHistoryList;


    @OneToMany(mappedBy="user", cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Feedback> feedbackList;

    @OneToMany(mappedBy="user", cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Wish> wishList;

    @OneToMany(mappedBy="user", cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<House> houseList;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>();

    @PrePersist
    private void init () {
        dateCreate = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", passwordOld='" + passwordOld + '\'' +
                ", active=" + active +
                ", role=" + roles +
                ", roleForm=" + roleForm +
                '}';
    }

}
