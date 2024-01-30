package com.example.cinema.controller;

import com.example.cinema.dto.request.MovieRequest;
import com.example.cinema.dto.response.MovieResponse;
import com.example.cinema.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movie")
@CrossOrigin
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;
    @PostMapping("/add-new")
    public String addNewMovie(@RequestBody MovieRequest dto){
        String status = movieService.addNewMovie(dto);
        return status;
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<MovieResponse>> getAll(){
        List<MovieResponse> movieResponseList = movieService.getAllMovie();
        return new ResponseEntity<>(movieResponseList, HttpStatus.OK);
    }

    @GetMapping("/getDetailMovie/{id}")
    public ResponseEntity<MovieResponse> getDetailMovie(@PathVariable("id")Long id){
        MovieResponse movieResponse = movieService.getDetailMovie(id);
        return new ResponseEntity<>(movieResponse,HttpStatus.OK);
    }

    @PostMapping("/addSiteForMovie/{id1}/{id2}")
    public String addSiteForMovue(@PathVariable("id1")Long id1, @PathVariable("id2") Long id2){
        String status = movieService.addSiteForMovie(id1,id2);
        return status;
    }
}
