package com.example.cinema.dto.request;

import lombok.Data;

@Data
public class TicketCustomeRequest {
    private Long idMovie;
    private Long idSite;
    private String time ;
    private String day;
}
