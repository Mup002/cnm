package com.example.cinema.dto.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CinemaCustomResponse {
    private Long idSite;
    private String cinemaType;
    private List<String> showtimes = new ArrayList<>();
}
