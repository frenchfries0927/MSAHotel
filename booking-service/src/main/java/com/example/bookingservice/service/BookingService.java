package com.example.bookingservice.service;

import com.example.bookingservice.entity.Booking;
import com.example.bookingservice.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    // 예약 생성
    public Booking createBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    // 전체 예약 조회
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    // 예약 ID로 조회
    public Optional<Booking> getBookingById(Long id) {
        return bookingRepository.findById(id);
    }
}
