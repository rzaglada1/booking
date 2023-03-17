package com.rzaglada1.booking.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    private String name;
    private String fileName;
    private long size;
    private String contentType;
    @Column(columnDefinition = "LONGBLOB")
    private byte[] photoToBytes;

    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private House house;

    private LocalDateTime dateCreate;


    @PrePersist
    private void init () {
        dateCreate = LocalDateTime.now();
    }

}
