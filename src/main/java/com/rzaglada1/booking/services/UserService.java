package com.rzaglada1.booking.services;

import com.rzaglada1.booking.models.User;
import com.rzaglada1.booking.models.enams.Role;
import com.rzaglada1.booking.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
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


    public List<User> getAll() {
        return userRepository.findAll();
    }

    public Optional<User> getById(long id) {
        return userRepository.findById(id);
    }


    public boolean isEmpty () {
        return userRepository.size() == 0;
    }



    public void update(User user, long userId) {
        if (userRepository.findById(userId).isPresent()) {
            User userUpdate = userRepository.findById(userId).get();
            //update Role
            Set <Role> roleSet = userUpdate.getRoles();
            roleSet.clear();
            roleSet.add(user.getRoleForm());
            userUpdate.setRoles(roleSet);

            userUpdate.setFirstName(user.getFirstName());
            userUpdate.setLastName(user.getLastName());
            userUpdate.setPhone(user.getPhone());
            userUpdate.setActive(user.getActive());

            if (isTruePassword(userId, user.getPasswordOld())) {
                userUpdate.setPassword(passwordEncoder.encode(user.getPassword()));
            }
            userRepository.save(userUpdate);
        }

    }


    public User getUserByPrincipal(Principal principal) {
        User user = new User();
        if (principal != null) {
            user = userRepository.findByEmail(principal.getName());
        }
        return user;
    }

    private Boolean isTruePassword(Long id, String password) {
        boolean isCheck = false;
        if (userRepository.findById(id).isPresent()) {
            isCheck = passwordEncoder.matches(password, userRepository.findById(id).get().getPassword());
        }
        return isCheck;
    }


}
