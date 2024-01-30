package com.example.cinema.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.*;

@Data
@Table(name = "movies")
@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private Long id;
    private String rating;
    private String img;
    @Column(name = "movie_name")
    private String name;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "movie_category", joinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "movie_id"), inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "category_id"))
    private List<Category> categories = new ArrayList<>();
    private String director;
    private String actor;
    private String language;
    private String timing;
    private Date startTime;
    private String description;
    public void addCategory(Category category){
        categories.add(category);
    }
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "movie_site", joinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "movie_id"),
    inverseJoinColumns = @JoinColumn(name = "site_id",referencedColumnName = "site_id"))
    private List<Site> sites = new ArrayList<>();
    public void addSite(Site site){
        sites.add(site);
    }
}
