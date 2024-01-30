package com.example.cinema.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "screentypes")
public class ScreenType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = " id_screentype")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_site")
    private Site site;
    @ManyToOne
    @JoinColumn(name = "id_screen")
    private Screen screen;
}
