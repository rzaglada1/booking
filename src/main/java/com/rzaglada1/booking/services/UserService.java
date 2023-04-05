package com.rzaglada1.booking.services;

import com.rzaglada1.booking.models.User;
import com.rzaglada1.booking.models.enams.Role;
import com.rzaglada1.booking.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;


    public void saveToBase(User user) {
        user.setActive(true);
        // if first user then role = ADMIN
        if (repository.findAll().isEmpty()) {
            user.getRoles().add(Role.ROLE_ADMIN);
        } else {
            user.getRoles().add(Role.ROLE_USER);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        repository.save(user);
    }

    public void deleteById(long id) {
        System.out.println("delete 12124124 ");
        repository.delete(repository.getReferenceById(id));
    }

    public List<User> getAll() {
        return repository.findAll();
    }

    public Optional<User> getById(long id) {
        return repository.findById(id);
    }

    public void update(User user, long id) {
        Optional<User> userOptional = getById(id);

        if (userOptional.isPresent()) {
            User userUpdate = userOptional.get();

            userUpdate.setFirstName(user.getFirstName());
            userUpdate.setLastName(user.getLastName());
            userUpdate.setPhone(user.getPhone());
            if (isTruePassword(id, user.getPasswordOld())) {
                userUpdate.setPassword(passwordEncoder.encode(user.getPassword()));
            }

            repository.save(userUpdate);
        }
    }


    public User getUserByPrincipal (Principal principal) {
        User user = new User();
        if (principal != null) {
                user = repository.findByEmail(principal.getName());
        }
        return user;
    }

    private Boolean isTruePassword(Long id, String password) {
        boolean isCheck = false;
        if (repository.findById(id).isPresent()) {
            isCheck = passwordEncoder.matches(password, repository.findById(id).get().getPassword());
        }
        System.out.println("11111111 " + isCheck);
        return isCheck;
    }

}
