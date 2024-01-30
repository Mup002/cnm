package com.example.cinema.dto.request;
import jakarta.persistence.*;
import lombok.Data;

import java.util.*;

@Data
public class MovieRequest {
    private String rating;
    private String img;
    private String name;
    private List<Long> categoriesId = new ArrayList<>();
    private String timing;
    private Date startTime;
    private String director;
    private String actor;
    private String language;
    private String description;
}
