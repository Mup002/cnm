package com.example.cinema.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "sitedeatils")
public class SiteDetails {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "id_sitedetails")
    private Long id;
    private String seat_type;
    private Double price_seat;
    private Long quantity_seats;
    @ManyToOne
    @JoinColumn(name = "id_site")
    private Site site;

}
