package com.example.cinema.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "img_sties")
public class ImgSite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "img_id")
    private Long id;
    private String img;
    @ManyToOne
    @JoinColumn(name = "id_site")
    private Site site;
}
