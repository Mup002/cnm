package com.example.cinema.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ticket")
    private Long id;
    private Double totalPrice;
    private Date createdDate;
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
    @ManyToOne
    @JoinColumn(name = "id_showtimeSite")
    private ShowtimeSite showtimeSite;
    @ManyToOne
    @JoinColumn(name = "id_movie")
    private Movie movie;
    private String seatNumber;
    private String seatType;
}
