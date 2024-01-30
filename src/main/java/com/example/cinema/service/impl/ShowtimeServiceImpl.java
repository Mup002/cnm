package com.example.cinema.service.impl;

import com.example.cinema.entity.Showtime;
import com.example.cinema.repository.ShowtimeRepository;
import com.example.cinema.service.ShowtimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShowtimeServiceImpl implements ShowtimeService {
    private final ShowtimeRepository showtimeRepository;
    @Override
    public List<Showtime> findByDay(String date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date formatted = new Date();
        try {
            formatted = format.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return showtimeRepository.findShowtimeByDay(formatted).stream().collect(Collectors.toList());
    }

    @Override
    public Showtime findShowtimeById(Long id) {
        return showtimeRepository.findShowtimeById(id);
    }
}
