package com.rzaglada1.booking.services;

import com.rzaglada1.booking.models.House;
import com.rzaglada1.booking.models.Image;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class HouseService {



    public Image fileToImage(MultipartFile file, House house) throws IOException {
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



}
