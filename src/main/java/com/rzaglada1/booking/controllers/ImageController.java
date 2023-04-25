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

 }
