package com.example.cinema.service;

import com.example.cinema.entity.Showtime;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ShowtimeService {
    List<Showtime> findByDay(String date);
    Showtime findShowtimeById(Long id);
}
