package com.rzaglada1.booking.controllers;

import com.rzaglada1.booking.models.Image;
import com.rzaglada1.booking.repositories.ImageRepository;
import com.rzaglada1.booking.services.HouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping(value = "/images")
@RequiredArgsConstructor
public class ImageController {
    private final HouseService houseService;
    private final ImageRepository imageRepository;

    @GetMapping("/{id}")
    private ResponseEntity<?> getImageById (@PathVariable long id) {
        ResponseEntity<?> responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        Optional<Image> image = imageRepository.findById(id);
        if (image.isPresent()) {
            responseEntity = ResponseEntity.ok()
                    .header("fileName", image.get().getFileName())
                    .contentType(MediaType.valueOf(image.get().getContentType()))
                    .contentLength(image.get().getSize())
                    .body(new InputStreamResource(new ByteArrayInputStream(image.get().getPhotoToBytes())));
        }
        return responseEntity;
    }

    // for test, delete me
    @PostMapping
    public String imageToBase (@RequestParam("file") MultipartFile file) {
        try {
            System.out.println("111111111111");
            Image image = new Image();
            image.setName(file.getName());
            image.setFileName(file.getOriginalFilename());
            image.setContentType(file.getContentType());
            image.setSize(file.getSize());
            image.setPhotoToBytes(file.getBytes());


            System.out.println(file.getSize());
            System.out.println(houseService.getById(1));
            image.setHouse(houseService.getById(1).get());
            houseService.getById(1).get().setImage(image);
            System.out.println("22222222");
            imageRepository.save(image);
        } catch (IOException e) {
            System.out.println("Something wrong");
        }
        return "user/user_new";
    }
}
