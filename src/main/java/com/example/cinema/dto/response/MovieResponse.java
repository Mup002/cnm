package com.example.cinema.dto.response;

import lombok.Data;

import java.util.*;

@Data
public class MovieResponse {
    private Long id;
    private String rating;
    private String img;
    private String name;
    private List<String> categoriesName =  new ArrayList<>();
    private String timing;
    private String startTime;
    private String director;
    private String actor;
    private String language;
    private String description;
}
