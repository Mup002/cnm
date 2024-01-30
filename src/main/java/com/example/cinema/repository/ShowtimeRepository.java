package com.example.cinema.repository;

import com.example.cinema.entity.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ShowtimeRepository extends JpaRepository<Showtime, Long> {
    Showtime findShowtimeById(Long id);
    List<Showtime> findShowtimeByDay(Date date);
    Showtime findShowtimeByTimeAndDay(String time, Date day);
}
