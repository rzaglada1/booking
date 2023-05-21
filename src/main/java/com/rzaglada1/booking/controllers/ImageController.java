package com.rzaglada1.booking.controllers;

import com.rzaglada1.booking.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping(value = "/images")
@RequiredArgsConstructor
public class ImageController {
    private final UserService userService;

    @GetMapping("/{id}")
                    private ResponseEntity<byte[]> getImage(@PathVariable("id") String id) {
        String uriImageId = "http://localhost:8079/images/" + id;
        RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = userService.getHeaders(AuthController.token);

        ResponseEntity<byte[]> result = restTemplate.exchange(uriImageId, HttpMethod.GET, new HttpEntity<>(headers), byte[].class );

        byte[] image = result.getBody();

        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
    }


  }
