package com.example.cinema.dto.request;

import lombok.Data;

@Data
public class ShowtimeCustomRequest {
    private Long idMovie;
    private String address;
    private String day;
}
