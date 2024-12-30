package com.example.frontendservice.controller;

import com.example.frontendservice.entity.Booking;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
public class BookingController {

    private final RestTemplate restTemplate;

    @Value("${api.gateway.url}")
    private String gatewayUrl;

    public BookingController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // 전체 예약 조회 페이지
    @GetMapping("/bookings")
    public String getBookings(Model model) {
        try {
            String bookingsUrl = gatewayUrl + "/api/bookings";
            List<?> bookings = restTemplate.getForObject(bookingsUrl, List.class);
            model.addAttribute("bookings", bookings != null ? bookings : List.of());
        } catch (Exception e) {
            model.addAttribute("error", "Unable to fetch bookings: " + e.getMessage());
            model.addAttribute("bookings", List.of());
        }
        return "bookings";
    }
}
