package com.example.cinema.service;

import com.example.cinema.dto.response.CinemaCustomResponse;
import com.example.cinema.entity.Showtime;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TestService {
    List<String> findSiteByIdMovie(Long id);
    CinemaCustomResponse findShowtimeByIdSite(Long id);
    List<Showtime> findByDay(String date);
    Showtime findShowtimeById(Long id);

}
