package com.rzaglada1.booking.services;

import com.rzaglada1.booking.models.*;
import com.rzaglada1.booking.repositories.AddressRepository;
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
    private final HouseRepository repositoryHouse;
    private final UserRepository repositoryUser;
    private final AddressRepository repositoryAddress;


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
        repositoryHouse.save(house);
    }

    public List<House> getHouseByUser (User user) {
        return repositoryHouse.getHouseByUser(user);
    }

    public Optional<House> getHouseById (long id) {
        return repositoryHouse.findById(id);
    }

    private User getUserByPrincipal(Principal principal) {
        return repositoryUser.findByEmail(principal.getName());
    }

    private List<House> getAllHouse() {
        return repositoryHouse.findAll();
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
        repositoryHouse.delete(repositoryHouse.getReferenceById(id));
    }

    public List<House> getAll() {
        return repositoryHouse.findAll();
    }

    public Optional<House> getById(long id) {
        return repositoryHouse.findById(id);
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

            repositoryHouse.save(houseUpdate);
        }
    }

    //==============================================


    public List<House> filterHouses(String country, String city, LocalDate date, int days, int people) {
        List<House> houses = getAllHouse();


        if (!houses.isEmpty()) {
            houses = houseDestinationFilter(houses, country, city);
        }

        if (!date.isEqual(LocalDate.parse("1970-01-01")) && !houses.isEmpty()) {

            houses = dateFilter(houses, date, days);

        }


        if (people != 1 && !houses.isEmpty()) {
            houses = houses.stream().filter(e->e.getNumTourists() >=people).toList();
        }

        houses.forEach(System.out::println);


        System.out.println("1111111111111111111111111111111111111");

        return houses;
    }


    private List<House> houseDestinationFilter(List<House> houses, String country, String city) {
      //  String[] destinationSplit = destination.trim().split("[., ]");

        List<House> housesTemp;
        // for country
        if (!country.equals("-1") ) {
            housesTemp = houses.stream().filter(house ->
                    {
                        boolean isPresent = false;

                        if (house.getAddress().getCountry().toUpperCase().trim().contains(country.toUpperCase().trim())
                                && country.toUpperCase().trim().length() != 0) {
                            isPresent = true;
                        } else {
                            isPresent = false;
                        }

                        return isPresent;
                    }
            ).collect(Collectors.toList());
            System.out.print("111 ");

            if (!housesTemp.isEmpty()) {
                houses = housesTemp;
            }
            housesTemp.forEach(System.out::println);
        }


        // for city
        if (!city.equals("-1") ) {
            housesTemp = houses.stream().filter(house ->
                    {
                        boolean isPresent = true;

                        if (house.getAddress().getCity().toUpperCase().trim().contains(city.toUpperCase().trim())
                                && city.toUpperCase().trim().length() != 0) {
                            isPresent = true;
                        } else {
                            isPresent = false;
                        }

                        return isPresent;
                    }
            ).collect(Collectors.toList());
            System.out.print("222 ");
            housesTemp.forEach(System.out::println);
            if (!housesTemp.isEmpty()) {
                houses = housesTemp;
            }
        }



        return houses;
    }


    private List<House> dateFilter(List<House> houses, LocalDate startDateBooking, int days) {
        List<House> houseListTemp = new ArrayList<>();
        LocalDate endDateBooking = startDateBooking.plusDays(days);

        System.out.println("startDateBooking" + startDateBooking);
        System.out.println("endDateBooking " + endDateBooking);

        LocalDate startDateHouse;
        LocalDate endDateHouse;

        for (House house : houses) {
            boolean flag = false;
            if (house.getOrderHistoryList().isEmpty()) {flag = true;}
                for (OrderHistory orderHistory : house.getOrderHistoryList()) {
                    flag = false;
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
                        flag = true;
                    } else {
                        break;
                    }

                    System.out.println("startDateHouse " + startDateHouse);
                    System.out.println("endDateHouse " + endDateHouse);
                    System.out.println(flag);

                }


                System.out.println("flag " + flag);
                if (flag) {
                    houseListTemp.add(house);
                }

        }
        System.out.println("-------------------------");
        houseListTemp.forEach(System.out::println);
        System.out.println("-------------------------");

        return houseListTemp;
    }



}
