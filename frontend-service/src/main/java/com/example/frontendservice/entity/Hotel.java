package com.example.frontendservice.entity;

import lombok.Data;

@Data
public class Hotel {
    private Long id;
    private String name;
    private String location;
    private String description;
    private Double pricePerNight;
}