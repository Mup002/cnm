package com.example.cinema.controller.test;

import com.example.cinema.dto.response.CinemaCustomResponse;
import com.example.cinema.entity.Showtime;
import com.example.cinema.service.ShowtimeService;
import com.example.cinema.service.TestService;
import com.example.cinema.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/test")
@RequiredArgsConstructor
public class TestController {
    private final TestService testService;
    private final ShowtimeService showtimeService;
    private final TicketService ticketService;
    @GetMapping("/findSite/{id}")
    public ResponseEntity<List<String>> findByIdMovie(@PathVariable("id")Long id){
        List<String> sites = testService.findSiteByIdMovie(id);
        return new ResponseEntity<>(sites, HttpStatus.OK);
    }
    @GetMapping("/findShow/{id}")
    public ResponseEntity<CinemaCustomResponse> findByIdSite(@PathVariable("id")Long id){
        CinemaCustomResponse cinemaCustomeResponse = testService.findShowtimeByIdSite(id);
        return new ResponseEntity<>(cinemaCustomeResponse,HttpStatus.OK);
    }
    @GetMapping("/findShowtimeByDate/{date}")
    public ResponseEntity<List<Showtime>> findAllByDate(@PathVariable("date")String date){
        List<Showtime> showtimeList = showtimeService.findByDay(date);
        return new ResponseEntity<>(showtimeList, HttpStatus.OK);
    }


}
