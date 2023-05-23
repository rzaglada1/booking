package com.rzaglada1.booking.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Image {

    private long id;

    private String name;
    private String fileName;
    private long size;
    private String contentType;
    @Column(columnDefinition = "LONGBLOB")
    private byte[] photoToBytes;

    @JsonIgnore
    private House house;

    private LocalDateTime dateCreate;


    @PrePersist
    private void init () {
        dateCreate = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", fileName='" + fileName + '\'' +
                ", size=" + size +
                ", contentType='" + contentType + '\'' +
//                ", photoToBytes=" + Arrays.toString(photoToBytes) +
//                ", house=" + house +
                ", dateCreate=" + dateCreate +
                '}';
    }
}
