package com.example.cinema.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "sites")
public class Site {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "site_id")
    private Long id;
    @Column(name = "site_name")
    private String name;
//    private String cinema;
    @OneToMany(mappedBy = "site",cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    private List<ShowtimeSite> showtimeSiteList = new ArrayList<>();
    public void addSite(ShowtimeSite site){
        showtimeSiteList.add(site);
    }
    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;
    @ManyToMany()
    private List<Movie> movies = new ArrayList<>();
    public void addMovie(Movie movie){
        movies.add(movie);
    }
    private String addressDetails;
    private String floor;
    private String fax;
    private String hotline;
    @OneToMany(mappedBy = "site",cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    private List<ScreenType> screenTypesList = new ArrayList<>();
}
