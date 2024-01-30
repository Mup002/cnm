package com.example.cinema.repository;


import com.example.cinema.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    Movie findMovieById(Long id);
    Movie findMovieByName(String name);

}
