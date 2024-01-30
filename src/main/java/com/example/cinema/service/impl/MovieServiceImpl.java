package com.example.cinema.service.impl;

import com.example.cinema.dto.request.MovieRequest;
import com.example.cinema.dto.response.MovieResponse;
import com.example.cinema.entity.Category;
import com.example.cinema.entity.Movie;
import com.example.cinema.entity.Site;
import com.example.cinema.repository.CategoryRepository;
import com.example.cinema.repository.MovieRepository;
import com.example.cinema.repository.SiteRepository;
import com.example.cinema.service.MovieService;
import com.example.cinema.utils.mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;
    private final CategoryRepository categoryRepository;
    private final SiteRepository siteRepository;
    @Override
    public String addNewMovie(MovieRequest request) {
        Movie movie = new Movie();
        movie.setName(request.getName());
        movie.setImg(request.getImg());
        movie.setTiming(request.getTiming());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        movie.setStartTime(request.getStartTime());
        if (request.getRating() != null) {
            movie.setRating(request.getRating());
        } else {
            movie.setRating("Khong co danh gia");
        }
        List<Long> cateList = request.getCategoriesId();
        for(Long id : cateList){
            Category category = categoryRepository.findCategoryById(id);
            category.addMovie(movie);
            movie.addCategory(category);
        }
        movie.setActor(request.getActor());
        movie.setDirector(request.getDirector());
        movie.setLanguage(request.getLanguage());
        movie.setDescription(request.getDescription());
        movieRepository.save(movie);
        return "done";
    }

    @Override
    public List<MovieResponse> getAllMovie() {
        return mapper.mapperToLstMovieResponse(movieRepository.findAll().stream().toList());
    }

    @Override
    public MovieResponse getDetailMovie(Long id) {
        return mapper.mapperToMovieResponse(movieRepository.findMovieById(id));
    }

    @Override
    public String addSiteForMovie(Long id1, Long id2) {
        Movie movie = movieRepository.findMovieById(id1);
        Site site = siteRepository.findSiteById(id2);
        movie.addSite(site);
        site.addMovie(movie);
        movieRepository.save(movie);
        return "done";
    }
}
