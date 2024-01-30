package com.example.cinema.dto.response;

import lombok.Data;

@Data
public class SiteDetailsResponse {
    private Long id_details;
    private String seat_type;
    private Double price_seat;
    private Long quantity_seats;
    private Long id_site;
}
