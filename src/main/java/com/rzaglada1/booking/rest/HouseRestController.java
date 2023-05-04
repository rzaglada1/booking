package com.rzaglada1.booking.rest;

import com.rzaglada1.booking.services.HouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/houses")
@RequiredArgsConstructor
public class HouseRestController {
    private final HouseService houseService;


}
