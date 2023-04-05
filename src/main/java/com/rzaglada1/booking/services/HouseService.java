package com.rzaglada1.booking.services;

import com.rzaglada1.booking.models.*;
import com.rzaglada1.booking.repositories.HouseRepository;
import com.rzaglada1.booking.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HouseService {
    private final HouseRepository repository;
    private final UserRepository repositoryUser;


    public void saveToBase(Principal principal, House house, Address address, MultipartFile file) throws IOException {
        // set image
        if (file != null && file.getSize() != 0) {
            house.setImage(fileToImage(file, house));
        }

        // set User
        house.setUser(getUserByPrincipal(principal));

        // set address
        house.setAddress(address);
        address.setHouse(house);

        // clear spase in description
        house.setDescription(house.getDescription().trim());
        repository.save(house);
    }

    private User getUserByPrincipal(Principal principal) {
        return repositoryUser.findByEmail(principal.getName());
    }

    private List<House> getAllHouse() {
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
            houseUpdate.setDescription(house.getDescription().trim());
            houseUpdate.setPrice(house.getPrice());
            houseUpdate.setIsAvailable(house.getIsAvailable());
            houseUpdate.setNumTourists(house.getNumTourists());

            addressUpdate.setCountry(address.getCountry());
            addressUpdate.setCity(address.getCity());
            addressUpdate.setStreet(address.getStreet());
            addressUpdate.setNumber(address.getNumber());
            addressUpdate.setApartment(address.getApartment());


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

    public List<House> filterHouses(String destination, LocalDate startDateBooking, int days, int people) {
        List<House> houses = getAllHouse();


        if (destination != "-1" && !houses.isEmpty()) {
            houses = houseDestinationFilter(houses, destination);
        }

//        if (!startDateBooking.isEqual(LocalDate.parse("1970-01-01")) && !houses.isEmpty()) {
//            LocalDate endDateBooking = startDateBooking.plusDays(days);
//
//            System.out.println("startDateBooking " + startDateBooking);
//            System.out.println("endDateBooking " + endDateBooking);
//
//                 houses = houses.stream().filter(h -> {
//                     boolean flag = false;
//                     LocalDate startDateHouse = LocalDate.parse("1960-01-01");
//                     LocalDate endDateHouse = LocalDate.parse("1960-01-01");
//
//                     if (h.getDataBooking() != null) {
//                         startDateHouse = h.getDataBooking();
//                         endDateHouse = startDateHouse.plusDays(h.getNumDaysBooking());
//                     }
//
//                     System.out.println("startDateHouse " + startDateHouse);
//                     System.out.println("endDateHouse " + endDateHouse);
//
//                  if(endDateBooking.isBefore(startDateHouse)
//                                   || endDateBooking.isEqual(startDateHouse)
//                           || endDateHouse.isBefore(startDateBooking)
//                                   || endDateHouse.isEqual(startDateBooking)
//                  ) {
//                      flag = true;
//                  }
//                     System.out.println("flag " + flag);
//                  return flag;
//                 } ).toList();
//        }


        if (!startDateBooking.isEqual(LocalDate.parse("1970-01-01")) && !houses.isEmpty()) {

            List<House> houseListTemp = new ArrayList<>();
            LocalDate endDateBooking = startDateBooking.plusDays(days);

            System.out.println("startDateBooking" + startDateBooking);
            System.out.println("endDateBooking " + endDateBooking);

            LocalDate startDateHouse;
            LocalDate endDateHouse;

            for (House house : houses) {
                boolean flag = true;
                for (OrderHistory orderHistory : house.getOrderHistoryList()) {
                    if (orderHistory.getDataBooking() != null) {
                        startDateHouse = orderHistory.getDataBooking();
                        endDateHouse = startDateHouse.plusDays(orderHistory.getNumDaysBooking());
                    } else {
                         startDateHouse = LocalDate.parse("1960-01-01");
                         endDateHouse = LocalDate.parse("1960-01-01");
                    }


                    if (endDateBooking.isBefore(startDateHouse)
                            || endDateBooking.isEqual(startDateHouse)
                            || endDateHouse.isBefore(startDateBooking)
                            || endDateHouse.isEqual(startDateBooking)
                    ) {
                        flag = false;
                    }

                    System.out.println("startDateHouse " + startDateHouse);
                    System.out.println("endDateHouse " + endDateHouse);

                }
                System.out.println("flag " + flag);
                if (flag) {
                    houseListTemp.add(house);
                }



            }
            System.out.println("-------------------------");
            houseListTemp.forEach(System.out::println);
            System.out.println("-------------------------");


        }


        if (people != 0 && !houses.isEmpty()) {

        }

        houses.forEach(System.out::println);


        System.out.println("1111111111111111111111111111111111111");

        return houses;
    }


    private List<House> houseDestinationFilter(List<House> houses, String destination) {
        List<String> destinationSplit = Arrays.asList(destination.trim().split("[., ]"));

        List<House> housesTemp;
        // for country
        housesTemp = houses.stream().filter(house ->
                {
                    boolean isPresent = false;
                    for (String s : destinationSplit) {
                        if (house.getAddress().getCountry().toUpperCase().trim().contains(s.toUpperCase().trim())
                                && s.toUpperCase().trim().length() != 0) {
                            isPresent = true;
                            break;
                        } else {
                            isPresent = false;
                        }
                    }
                    return isPresent;
                }
        ).collect(Collectors.toList());
        System.out.print("111 ");

        if (!housesTemp.isEmpty()) {
            houses = housesTemp;
        }
        housesTemp.forEach(System.out::println);

        // for city
        housesTemp = houses.stream().filter(house ->
                {
                    boolean isPresent = true;
                    for (String s : destinationSplit) {
                        if (house.getAddress().getCity().toUpperCase().trim().contains(s.toUpperCase().trim())
                                && s.toUpperCase().trim().length() != 0) {
                            isPresent = true;
                            break;
                        } else {
                            isPresent = false;
                        }
                    }
                    return isPresent;
                }
        ).collect(Collectors.toList());
        System.out.print("222 ");
        housesTemp.forEach(System.out::println);
        if (!housesTemp.isEmpty()) {
            houses = housesTemp;
        }

        // for street
        housesTemp = houses.stream().filter(house ->
                {
                    boolean isPresent = false;
                    for (String s : destinationSplit) {
                        if (house.getAddress().getStreet().toUpperCase().trim().contains(s.toUpperCase().trim())
                                && s.toUpperCase().trim().length() != 0) {
                            isPresent = true;
                            break;
                        } else {
                            isPresent = false;
                        }
                    }
                    return isPresent;
                }
        ).collect(Collectors.toList());
        System.out.print("333 ");
        housesTemp.forEach(System.out::println);
        System.out.println("========================");
        if (!housesTemp.isEmpty()) {
            houses = housesTemp;
        }

        return houses;
    }


}
