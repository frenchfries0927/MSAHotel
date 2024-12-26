package com.example.frontendservice.entity;

import lombok.Data;
import java.time.LocalDate;

@Data
public class Booking {
    private Long id;
    private Long userId;
    private Long hotelId;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private Double totalPrice;

    // Getter 메서드 추가
    public Long getId() { return id; }
    public Long getUserId() { return userId; }
    public Long getHotelId() { return hotelId; }
    public LocalDate getCheckInDate() { return checkInDate; }
    public LocalDate getCheckOutDate() { return checkOutDate; }
    public Double getTotalPrice() { return totalPrice; }

}
