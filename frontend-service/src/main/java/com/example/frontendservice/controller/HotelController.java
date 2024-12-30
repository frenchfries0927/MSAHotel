package com.example.frontendservice.controller;

import com.example.frontendservice.entity.Hotel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
public class HotelController {

    private final RestTemplate restTemplate;
    private final String hotelServiceUrl;

    public HotelController(RestTemplate restTemplate, @Value("${hotel.service.url}") String hotelServiceUrl) {
        this.restTemplate = restTemplate;
        this.hotelServiceUrl = hotelServiceUrl;
    }
    @GetMapping("/hotels")
    public String getHotels(Model model) {
        try {
            Hotel[] hotels = restTemplate.getForObject(hotelServiceUrl, Hotel[].class);
            model.addAttribute("hotels", hotels);
            System.out.println("Hotels fetched: " + Arrays.toString(hotels));
        } catch (Exception e) {
            model.addAttribute("error", "Unable to fetch hotel data: " + e.getMessage());
            System.err.println("Error fetching hotels: " + e.getMessage());
        }
        return "hotels";
    }


}
