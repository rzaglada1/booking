package com.rzaglada1.booking.services;

import com.rzaglada1.booking.controllers.AuthController;
import com.rzaglada1.booking.models.User;
import com.rzaglada1.booking.models.enams.Role;
import com.rzaglada1.booking.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.security.Principal;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public boolean saveToBase(User user) {
        boolean isAllOk = false;
        if (userRepository.findByEmail(user.getEmail()) == null) {
            user.setActive(true);
            // if first user then role = ADMIN
            if (userRepository.findAll().isEmpty()) {
                user.getRoles().add(Role.ROLE_ADMIN);
            } else {
                user.getRoles().add(Role.ROLE_USER);
            }
            user.setPassword(passwordEncoder.encode(user.getPassword()));

            userRepository.save(user);
            isAllOk = true;
        }
        return isAllOk;
    }

    public void deleteById(long id) {
        userRepository.delete(userRepository.getReferenceById(id));
    }

    public void deleteByPrincipal(Principal principal) {
        if (principal != null && userRepository.findById(getUserByPrincipal(principal).getId()).isPresent()) {
            userRepository.delete(userRepository.findById(getUserByPrincipal(principal).getId()).get());
        }

    }


    public Page<User> getAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public Optional<User> getById(long id) {
        return userRepository.findById(id);
    }


    public boolean isEmpty () {
        return userRepository.size() == 0;
    }



    public boolean update(User user, long userId) {
        boolean isAllOk = false;
        if (userRepository.findById(userId).isPresent()) {
            User userUpdate = userRepository.findById(userId).get();
            //update Role
            Set <Role> roleSet = userUpdate.getRoles();

            if (user.getRoleForm() != null) {
                roleSet.clear();
                roleSet.add(user.getRoleForm());
                userUpdate.setRoles(roleSet);
            }

            userUpdate.setFirstName(user.getFirstName());
            userUpdate.setLastName(user.getLastName());
            userUpdate.setPhone(user.getPhone());
            userUpdate.setActive(user.getActive());
            if (user.getPasswordOld() !=null && user.getPasswordOld().length() != 0 ) {
                userUpdate.setPassword(passwordEncoder.encode(user.getPassword()));
            }
            userRepository.save(userUpdate);
            isAllOk = true;
        }

        return isAllOk;
    }


    public User getUserByPrincipal(Principal principal) {
        User user = new User();
        if (principal != null) {
            user = userRepository.findByEmail(principal.getName());
        }
        return user;
    }

    public Boolean isTruePassword(Long id, String password) {
        boolean isCheck = false;
        if (userRepository.findById(id).isPresent()) {
            isCheck = passwordEncoder.matches(password, userRepository.findById(id).get().getPassword());
        }
        return isCheck;
    }

    public HttpHeaders getHeaders(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.set("Authorization", token);
        return headers;
    }




    public  User getUserByToken(String token, String uri) {
        User user = new User();
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = getHeaders(token);
        HttpEntity<User> userAuth = new HttpEntity<>(headers);
        try {
            ResponseEntity<User> userResponseEntity = restTemplate.exchange(uri, HttpMethod.GET, userAuth, User.class);

            if (userResponseEntity.getStatusCode().is2xxSuccessful() && userAuth != null) {
                user = userResponseEntity.getBody();
            }
        } catch (HttpClientErrorException e) {
            System.out.println("error get param ");
            System.out.println(e.getMessage());
        }
        return user;
    }


    public void userLogout (String token, String uri) {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = getHeaders(token);
        HttpEntity<User> userAuth = new HttpEntity<>(headers);

        try {
            restTemplate.exchange(uri, HttpMethod.GET, userAuth, User.class);
            AuthController.token = null;

        } catch (HttpClientErrorException e) {
            e.printStackTrace();
        }
    }

}
