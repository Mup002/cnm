package com.example.cinema.service;

import com.example.cinema.dto.request.MovieRequest;
import com.example.cinema.dto.response.MovieResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MovieService {
    String addNewMovie(MovieRequest request);
    List<MovieResponse> getAllMovie();
    MovieResponse getDetailMovie(Long id);
    String addSiteForMovie(Long id1, Long id2);
}
