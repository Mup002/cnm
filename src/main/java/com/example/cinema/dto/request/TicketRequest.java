package com.example.cinema.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class TicketRequest {
    private String userName;
    private String movieName;
    private String day;
    private String time;
    private Double totalPrice;
    private List<String> seatNumbers;
    private String created;
    private String seatType;
    private Long idSite;
}
