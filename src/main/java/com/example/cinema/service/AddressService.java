package com.example.cinema.service;

import com.example.cinema.entity.Address;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AddressService {
    List<String> findByDayAndMovie(Long idMovie, String date);
}
