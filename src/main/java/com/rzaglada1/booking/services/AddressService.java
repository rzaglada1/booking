package com.rzaglada1.booking.services;

import com.rzaglada1.booking.models.Address;
import com.rzaglada1.booking.repositories.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository repository;


    public void saveToBase (Address address) {
        repository.save(address);
    }

    public Optional<Address> getById (long id) {
        return repository.findById(id);
    }
}
