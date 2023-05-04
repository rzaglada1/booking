package com.rzaglada1.booking.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.rzaglada1.booking.models.enams.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;


    @Column(unique = true)
    @Email(message = "Не вірний формат email")
    @NotBlank(message = "Це поле не повинно бути порожнім")
    @Length(max = 50, message = "Довжина не повинна перевищувати 50 символів")
    private String email;

    @NotBlank(message = "Це поле не повинно бути порожнім")
    @Length(max = 50, message = "Довжина не повинна перевищувати 50 символів")
    private String firstName;

    @NotBlank(message = "Це поле не повинно бути порожнім")
    @Length(max = 50, message = "Довжина не повинна перевищувати 50 символів")
    private String lastName;

    @NotBlank(message = "Це поле не повинно бути порожнім")
    @Length(max = 50, message = "Довжина не повинна перевищувати 50 символів")
    private String phone;

    @Column(name = "password", length = 1000)
    @NotBlank(message = "Це поле не повинно бути порожнім")
    @Length(max = 1000, message = "Довжина не повинна перевищувати 50 символів")
    private String password;
    @Transient
    @Length(max = 1000, message = "Довжина не повинна перевищувати 50 символів")
    @JsonIgnore
    private String passwordConfirm;
    @Transient
    @Length(max = 1000, message = "Довжина не повинна перевищувати 50 символів")
    @JsonIgnore
    private String passwordOld;
    @Transient
    @JsonIgnore
    private Role roleForm;
    @Transient
    @JsonIgnore
    private String activeForm;

    private Boolean active;

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
                ", dateCreate=" + dateCreate +
                ", role=" + roles +
                '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}
