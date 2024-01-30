package com.example.cinema.controller;

import com.example.cinema.entity.Showtime;
import com.example.cinema.entity.Site;
import com.example.cinema.service.ShowtimeService;
import com.example.cinema.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/showtime")
@RequiredArgsConstructor
public class ShowtimeController {
    private final ShowtimeService showtimeService;
    @GetMapping("/findShowtime/{date}")
    public ResponseEntity<List<Showtime>> find(@PathVariable("date")String date){
        List<Showtime> showtimes = showtimeService.findByDay(date);
        return new ResponseEntity<>(showtimes, HttpStatus.OK);
    }

}
