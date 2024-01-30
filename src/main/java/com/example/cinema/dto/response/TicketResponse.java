package com.example.cinema.dto.response;

import lombok.Data;

@Data
public class TicketResponse {
    private String userName;
    private String movieName;
    private String day;
    private String time;
    private Double totalPrice;
    private String seatNumber;
    private String created;
    private String seatType;
    private String cinema;
    private String siteName;
}
