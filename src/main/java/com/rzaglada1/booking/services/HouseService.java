package com.rzaglada1.booking.services;

import com.rzaglada1.booking.models.Address;
import com.rzaglada1.booking.models.Image;
import com.rzaglada1.booking.models.House;
import com.rzaglada1.booking.models.User;
import com.rzaglada1.booking.repositories.HouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HouseService {
    private final HouseRepository repository;


    public void saveToBase(House house, Address address, MultipartFile file) throws IOException {
        // set image
        if (file != null && file.getSize() != 0) {
            house.setImage(fileToImage(file, house));
        }

        // set address
        house.setAddress(address);
        address.setHouse(house);

        repository.save(house);
    }

    private List<House>  getAllHouse () {
        return repository.findAll();
    }

    private Image fileToImage(MultipartFile file, House house) throws IOException {
        // set image
        Image image = new Image();
        image.setHouse(house);
        image.setName(file.getName());
        image.setFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setPhotoToBytes(file.getBytes());

        return image;
    }

    public void deleteById(long id) {
        repository.delete(repository.getReferenceById(id));
    }

    public List<House> getAll() {
        return repository.findAll();
    }

    public Optional<House> getById(long id) {
        return repository.findById(id);
    }


    public void update(House house, Address address, MultipartFile file, Long id) throws IOException {
        Optional<House> houseOptional = getById(id);
        if (houseOptional.isPresent()) {

            House houseUpdate = houseOptional.get();
            Address addressUpdate = houseUpdate.getAddress();
            Image imageUpdate;
            if (houseUpdate.getImage() == null) {
                imageUpdate = new Image();
                imageUpdate.setHouse(houseUpdate);
            } else {
                imageUpdate = houseUpdate.getImage();
            }

            houseUpdate.setName(house.getName());
            houseUpdate.setDescription(house.getDescription());
            houseUpdate.setPrice(house.getPrice());
            houseUpdate.setIsAvailable(house.getIsAvailable());
            houseUpdate.setNumTourists(house.getNumTourists());

            addressUpdate.setCountry(address.getCountry());
            addressUpdate.setCity(address.getCity());
            addressUpdate.setStreet(address.getStreet());
            addressUpdate.setNumber(address.getNumber());

            // set image
            if (file != null && file.getSize() != 0) {
                imageUpdate.setName(file.getName());
                imageUpdate.setFileName(file.getOriginalFilename());
                imageUpdate.setContentType(file.getContentType());
                imageUpdate.setSize(file.getSize());
                imageUpdate.setPhotoToBytes(file.getBytes());
                houseUpdate.setImage(imageUpdate);
            }
            // set address
            houseUpdate.setAddress(addressUpdate);

            repository.save(houseUpdate);
        }
    }
}
