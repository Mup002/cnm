package com.example.cinema.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Table(name = "categories")
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;
    @Column(name = "category_name")
    private String name;
    @ManyToMany(mappedBy = "categories",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Movie> movies = new ArrayList<>();
    public void addMovie(Movie movie){
        movies.add(movie);
    }
}
