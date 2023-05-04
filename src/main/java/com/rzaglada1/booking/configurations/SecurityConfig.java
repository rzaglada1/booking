package com.rzaglada1.booking.configurations;

import com.rzaglada1.booking.services.SecurityUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig  {

    private final SecurityUserService securityUserService;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()


                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers( "/", "/find", "/images/*",
                                "/houses/*/detail", "/users/new", "/users").permitAll()
                        //.requestMatchers("/users").hasRole("ROLE_ADMIN")
                        .anyRequest().authenticated()
                        //.anyRequest().permitAll()

                )

                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll()
                )
                .logout()
                .logoutSuccessUrl("/")
                .permitAll();

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(8);
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }





}
