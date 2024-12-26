package com.example.hotelservice.service;

import com.example.hotelservice.entity.Hotel;
import com.example.hotelservice.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    // 호텔 등록
    public Hotel createHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    // 전체 호텔 조회
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    // 호텔 ID로 조회
    public Optional<Hotel> getHotelById(Long id) {
        return hotelRepository.findById(id);
    }
}
