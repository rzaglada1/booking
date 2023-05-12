package com.rzaglada1.booking.models;

import com.rzaglada1.booking.models.enams.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class RequestAuth {

    private String email;
    private String password;
    private String accessToken;
    private long id;
    private Boolean active;
    private Set<Role> role;

}
