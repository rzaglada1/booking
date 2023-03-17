package com.rzaglada1.booking.models.enams;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthoritiesContainer;

public enum Role implements GrantedAuthority {
    ROLE_ADMIN,
    ROLE_USER,
    ROLE_GUEST;

    @Override
    public String getAuthority() {
        return name();
    }
}
