package com.rzaglada1.booking.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rzaglada1.booking.models.*;
import com.rzaglada1.booking.models.enams.Role;
import com.rzaglada1.booking.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


@Controller
@RequestMapping(value = "/houses")
@RequiredArgsConstructor
public class HouseController {
    private final HouseService houseService;
    private final UserService userService;
    private final WishService wishService;
    private final OrderHistoryService orderHistoryService;
    private final FeedbackService feedbackService;


    // request form all  house

    @GetMapping
    public String houseList(
            Model model
            , @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC, size = 3) Pageable pageable) {

        String uriUserParam = "http://localhost:8079/users/param";
        String uriHouses = "http://localhost:8079/houses?page=" + pageable.getPageNumber();

        if (AuthController.token != null) {
            User userAuth = userService.getUserByToken(AuthController.token, uriUserParam);

            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = userService.getHeaders(AuthController.token);

            try {
                ParameterizedTypeReference<PaginatedResponse<House>> responseType = new ParameterizedTypeReference<>() {
                };
                ResponseEntity<PaginatedResponse<House>> result = restTemplate.exchange(
                        uriHouses
                        , HttpMethod.GET
                        , new HttpEntity<>(headers)
                        , responseType
                );

                Page<House> housePage = result.getBody();

                setModelAdmin(model, userAuth);

                model.addAttribute("houses", housePage);
                model.addAttribute("page", housePage);
                model.addAttribute("url", "/houses");

                model.addAttribute("user", userAuth);
                if (housePage.getTotalElements() == 0) {
                    model.addAttribute("message", "Поки нічого не має.");
                    return "/message";
                }
            } catch (HttpClientErrorException e) {
                e.printStackTrace();
            }

        } else {
            return "redirect:/auth/login";
        }
        return "house/house_list";
    }


    // request form new  house
    @GetMapping("/new")
    public String houseNew(Model model) {
        String uriUserParam = "http://localhost:8079/users/param";

        if (AuthController.token != null) {
            User userAuth = userService.getUserByToken(AuthController.token, uriUserParam);
            model.addAttribute("house", null);
            model.addAttribute("address", new Address());
            model.addAttribute("user", userAuth);
            setModelAdmin(model, userAuth);
        } else {
            return "redirect:/redirect:/auth/login";
        }
        return "house/house_form";
    }


    // create new house
    @PostMapping
    public String house(
            @RequestParam(value = "file", required = false) MultipartFile file
            , House house
            , Model model
    ) throws IOException {
        String uriUserParam = "http://localhost:8079/users/param";
        String uriHouseNew = "http://localhost:8079/houses";

        house.setImage(houseService.fileToImage(file, house));

        if (AuthController.token != null) {
            User userAuth = userService.getUserByToken(AuthController.token, uriUserParam);
            model.addAttribute("new", " ");
            //check is active
            checkActive(house);

            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = userService.getHeaders(AuthController.token);
            HttpEntity<House> houseEntity = new HttpEntity<>(house, headers);

            try {
                restTemplate.exchange(uriHouseNew, HttpMethod.POST, houseEntity, House.class);
            } catch (HttpClientErrorException e) {
                // if error validation

                try {
                    Map<String, String> errorsMap = getMapError(e.getMessage());
                    model.addAttribute("errorMessage", " ");
                    model.mergeAttributes(errorsMap);

                    model.addAttribute("user", userAuth);
                    model.addAttribute("house", house);
                    model.addAttribute("address", house.getAddress());
                    return "house/house_form";
                } catch (IOException ex) {
                    System.out.println(e.getMessage());
                }
            }

        } else {
            return "redirect:/auth/login";
        }
        return "redirect:/";
    }



    // update house
    @PostMapping("/{id}")
    public String houseUpdate(
            @RequestParam(value = "file", required = false) MultipartFile file
            , House house
            , Model model
            , @PathVariable long id
    ) throws IOException {

        String uriUserParam = "http://localhost:8079/users/param";
        String uriHouseUpdate = "http://localhost:8079/houses/" + id;

        if (file != null && file.getSize() !=0) {
            house.setImage(houseService.fileToImage(file, house));
        } else {
            house.setImage(null);
        }

        if (AuthController.token != null) {
            User userAuth = userService.getUserByToken(AuthController.token, uriUserParam);
            model.addAttribute("edit", " ");
            //check is active
            checkActive(house);

            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = userService.getHeaders(AuthController.token);
            HttpEntity<House> houseEntity = new HttpEntity<>(house, headers);

            try {
                restTemplate.exchange(uriHouseUpdate, HttpMethod.PUT, houseEntity, House.class);
            } catch (HttpClientErrorException e) {
                // if error validation
                try {
                    Map<String, String> errorsMap = getMapError(e.getMessage());
                    model.addAttribute("errorMessage", " ");
                    model.mergeAttributes(errorsMap);

                    model.addAttribute("imageId", house.getImageId());

                    model.addAttribute("user", userAuth);
                    model.addAttribute("house", house);
                    model.addAttribute("address", house.getAddress());
                    return "house/house_form";
                } catch (IOException ex) {
                    System.out.println(e.getMessage());
                }
            }

        } else {
            return "redirect:/auth/login";
        }

        return "redirect:/houses";
    }



    // request form edit house by id
    @GetMapping("/{id}/edit")
    public String houseEdit(@PathVariable Long id, Model model) {

        String uriUserParam = "http://localhost:8079/users/param";
        String uriHousesId = "http://localhost:8079/houses/" + id;



        if (AuthController.token != null) {
            User userAuth = userService.getUserByToken(AuthController.token, uriUserParam);

            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = userService.getHeaders(AuthController.token);

            try {
                ParameterizedTypeReference<House> responseType = new ParameterizedTypeReference<>() {
                };
                ResponseEntity<House> result = restTemplate.exchange(
                        uriHousesId
                        , HttpMethod.GET
                        , new HttpEntity<>(headers)
                        , responseType
                );
                House house = result.getBody();

                //for valid house error
                if (house != null && house.getImage() != null) {
                    house.setImageId(house.getImage().getId());
                }
                setModelAdmin(model, userAuth);
                model.addAttribute("edit", " ");
                model.addAttribute("house", house);
                model.addAttribute("address", house.getAddress());
                model.addAttribute("user", userAuth);

            } catch (HttpClientErrorException e) {
                e.printStackTrace();
            }
        } else {
            return "redirect:/auth/login";
        }
        return "house/house_form";
    }



    @GetMapping("/{id}/delete")
    public String houseDelete(@PathVariable("id") Long id) {
        String uriUserParam = "http://localhost:8079/users/param";
        String uriHousesDelete = "http://localhost:8079/houses/delete/" + id;

        if (AuthController.token != null) {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = userService.getHeaders(AuthController.token);
            HttpEntity<House> userEntity = new HttpEntity<>(headers);
            try {
                restTemplate.exchange(uriHousesDelete, HttpMethod.DELETE, userEntity, House.class);
            } catch (HttpClientErrorException e) {
                System.out.println(e.getMessage());
            }
        } else {
            return "redirect:/auth/login";
        }
        return "redirect:/houses";
    }


    @GetMapping("/{houseId}/detail")
    public String houseDetail(@PathVariable("houseId") Long houseId, Model model) {


        String uriUserParam = "http://localhost:8079/users/param";
        String uriHousesId = "http://localhost:8079/houses/" + houseId;
        String uriWishByHousesId = "http://localhost:8079/wishes/" + houseId;
        String uriFeedbackByHousesId = "http://localhost:8079/feedbacks/" + houseId;
        String uriHistoryByHousesId = "http://localhost:8079/orders/" + houseId;



        if (AuthController.token != null) {
            User userAuth = userService.getUserByToken(AuthController.token, uriUserParam);

            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = userService.getHeaders(AuthController.token);

            //get house
            try {
                ParameterizedTypeReference<House> responseType = new ParameterizedTypeReference<>() {
                };

                System.out.println("111");
                System.out.println(userAuth);

                ResponseEntity<House> result = restTemplate.exchange(
                        uriHousesId
                        , HttpMethod.GET
                        , new HttpEntity<>(headers)
                        , responseType
                );
                System.out.println("222");
                House house = result.getBody();
                setModelAdmin(model, userAuth);
                model.addAttribute("house", house);
                model.addAttribute("user", userAuth);

                System.out.println(house);


            } catch (HttpClientErrorException e) {
                e.printStackTrace();
            }

            // get wish list
            try {
                ParameterizedTypeReference<Wish> responseTypeWish = new ParameterizedTypeReference<>() {
                };
                ResponseEntity<Wish> resultWish = restTemplate.exchange(
                        uriWishByHousesId
                        , HttpMethod.GET
                        , new HttpEntity<>(headers)
                        , responseTypeWish
                );

                Wish wish = resultWish.getBody();

                if (wish != null) {
                    model.addAttribute("isWishList", true);
                }
            } catch (Exception e) {
                model.addAttribute("isWishList", false);
        }

            // get feedbacks
            try {
                ParameterizedTypeReference<List<Feedback>> responseTypeWish = new ParameterizedTypeReference<>() {
                };
                ResponseEntity<List<Feedback>> resultFeedback = restTemplate.exchange(
                        uriFeedbackByHousesId
                        , HttpMethod.GET
                        , new HttpEntity<>(headers)
                        , responseTypeWish
                );
                List<Feedback> feedbackList = resultFeedback.getBody();
                model.addAttribute("feedbacks", feedbackList);

                int countFeedback = feedbackList.size();
                double averRating = feedbackList.stream().mapToDouble(Feedback::getRating).average().orElseThrow();
                model.addAttribute("averRating", averRating);
                model.addAttribute("countFeedback", countFeedback);
            } catch (Exception  e) {
                model.addAttribute("averRating", -1);
                model.addAttribute("feedbacks", new ArrayList<Feedback>());
                model.addAttribute("countFeedback", 0);
            }

            // get orderHistory
            try {
                ParameterizedTypeReference<List<OrderHistory>> responseTypeWish = new ParameterizedTypeReference<>() {
                };
                ResponseEntity<List<OrderHistory>> resultOrderHistory = restTemplate.exchange(
                        uriHistoryByHousesId
                        , HttpMethod.GET
                        , new HttpEntity<>(headers)
                        , responseTypeWish
                );
                List<OrderHistory> orderHistoryList = resultOrderHistory.getBody();

                List <OrderHistory> forCalendarFree = orderHistoryList.stream()
                        .filter (e->e.getDataBookingEnd().isAfter(LocalDate.now() )
                        || e.getDataBookingEnd().equals(LocalDate.now()))
                        .sorted(Comparator.comparing(OrderHistory::getDataBookingStart))
                        .toList();

                if (!orderHistoryList.isEmpty()) {
                    model.addAttribute("orders", forCalendarFree);
                }

            } catch (Exception  e) {
                model.addAttribute("averRating", -1);

            }
        } else {
            return "redirect:/auth/login";
        }
        return "house/house_detail";
    }


    @PostMapping("/{houseId}/feedbacks")
    public String houseDetailFeedback(
            @PathVariable("houseId") Long houseId
            , Feedback feedback
            , Model model
    ) {

        String uriUserParam = "http://localhost:8079/users/param";
        String uriFeedbackNew = "http://localhost:8079/feedbacks/" + houseId;

        if (AuthController.token != null) {
            User userAuth = userService.getUserByToken(AuthController.token, uriUserParam);

            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = userService.getHeaders(AuthController.token);
            HttpEntity<Feedback> feedbackEntity = new HttpEntity<>(feedback, headers);

            try {
                House house = new House();
                house.setId(houseId);
                feedback.setUser(userAuth);
                feedback.setHouse(house);
                restTemplate.exchange(uriFeedbackNew, HttpMethod.POST, feedbackEntity, Feedback.class);
                return "redirect:/houses/" + houseId + "/detail";

            } catch (HttpClientErrorException e) {
                // if error validation
                return "redirect:/houses/" + houseId + "/detail";
            }
        } else {
            return "redirect:/auth/login";
        }

    }



    @PostMapping({"/{houseId}/prebooking", "/{houseId}/{booking}"})
    public String hosePreBooking(
            @PathVariable("houseId") long houseId
            , @PathVariable(value = "booking", required = false) String booking
            , OrderHistory orderHistory
            , Model model
             ) {

        String messageUrl = "/houses/" + houseId + "/detail";
        String uriUserParam = "http://localhost:8079/users/param";
        String uriPrebooking = "http://localhost:8079/orders/" + houseId + "/prebooking";



        if (orderHistory.getDataBookingStart() == null ) {
            return "redirect:" + messageUrl;
        }

        if (AuthController.token != null) {
            User userAuth = userService.getUserByToken(AuthController.token, uriUserParam);

            model.addAttribute("messageUrl", messageUrl);
            model.addAttribute("user", userAuth);
            setModelAdmin(model, userAuth);

            House house = new House();
            house.setId(houseId);
            LocalDate dataBookingEnd = orderHistory.getDataBookingStart().plusDays(orderHistory.getNumDaysBooking());
            orderHistory.setDataBookingEnd(dataBookingEnd);
            orderHistory.setUser(userAuth);
            orderHistory.setHouse(house);

            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = userService.getHeaders(AuthController.token);
            HttpEntity<OrderHistory> historyEntity = new HttpEntity<>(orderHistory, headers);

            try {


                if (booking != null && booking.equals("booking")) {
                    uriPrebooking = "http://localhost:8079/orders/" + houseId;
                }

                System.out.println("order  " + orderHistory);
                System.out.println("booking " + booking);
                System.out.println(uriPrebooking);

                ResponseEntity<OrderHistory> response = restTemplate.exchange(uriPrebooking, HttpMethod.POST, historyEntity, OrderHistory.class);
                OrderHistory orderHistoryResponse = response.getBody();

                if (booking != null && booking.equals("booking")) {
                    model.addAttribute("message", "Заброньовано");
                    return "/message";
                }

                if (orderHistoryResponse == null) {
                    model.addAttribute("message", "На вказаний період вже заброньовано. Або кількість людей не сумісна з будинком");
                    return "/message";
                }

                model.addAttribute("house", orderHistoryResponse.getHouse());
                model.addAttribute("order", orderHistoryResponse);
            } catch (HttpClientErrorException e) {
                // if error validation
                try {
                    if (booking != null && booking.equals("booking")) {
                        model.addAttribute("message", "Щось пішло не так");
                        return "/message";
                    }
                    Map<String, String> errorsMap = getMapError(e.getMessage());
                    model.mergeAttributes(errorsMap);
                    model.addAttribute("message", "Не вірно вказані критерії бронювання. Спробуйте їх змінити.");
                return "/message";
                } catch (IOException ex) {
                    System.out.println(e.getMessage());
                }
            }
        } else {
            return "redirect:/auth/login";
        }
        return "/house/house_booking";
    }


    private Map<String, String> mapErrors(BindingResult bindingResult) {
        return bindingResult.getFieldErrors().stream().collect(
                Collectors.toMap(fieldError -> fieldError.getField() + "Error", FieldError::getDefaultMessage));
    }




    private void setModelAdmin(Model model, User authUser) {
        if (authUser.getRoles().contains(Role.ROLE_ADMIN)) {
            model.addAttribute("admin", "admin");
        }
    }

    private void checkActive(House house) {
        house.setIsAvailable(house.getIsAvailableForm() != null);
    }


    private Map<String, String> getMapError(String responseError) throws JsonProcessingException {
        String startString = "400 : \"{\"";
        String endString = "\"";
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> mapError = new HashMap<>();
        if (responseError.startsWith(startString)) {
            int startIndex = responseError.indexOf(startString);
            int endIndex = responseError.lastIndexOf(endString);
            String jsonError = responseError.substring(startIndex + startString.length() - 2, endIndex);

            mapError = mapper.readValue(jsonError, Map.class);
        }

        return mapError;
    }


}
