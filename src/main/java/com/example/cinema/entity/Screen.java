package com.example.cinema.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "screens")
public class Screen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_screen")
    private Long id;
    private String screen;
    @OneToMany(mappedBy = "screen",cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    private List<ScreenType> screenTypesList = new ArrayList<>();
}
