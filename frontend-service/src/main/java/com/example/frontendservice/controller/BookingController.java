package com.example.frontendservice.controller;

import com.example.frontendservice.entity.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
@Controller
public class BookingController {
    private final RestTemplate restTemplate;

    @Value("${api.gateway.url}")
    private String gatewayUrl;

    public BookingController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/bookings")
    public String getBookings(Model model) {
        try {
            String bookingsUrl = gatewayUrl + "/bookings";
            System.out.println("Calling: " + bookingsUrl);

            Booking[] bookings = restTemplate.getForObject(bookingsUrl, Booking[].class);
            model.addAttribute("bookings", bookings != null ? bookings : new Booking[0]);
        } catch (Exception e) {
            System.err.println("Error fetching bookings: " + e.getMessage());
            model.addAttribute("error", "Unable to fetch bookings");
            model.addAttribute("bookings", new Booking[0]);
        }
        return "bookings";
    }
}