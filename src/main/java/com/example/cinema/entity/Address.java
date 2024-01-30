package com.example.cinema.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;

@Table(name = "address")
@Data
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long id;
    @Column(name = "address_name")
    private String name;
    @OneToMany(mappedBy = "address", cascade = ALL)
    private List<Site> sites = new ArrayList<>();
    public void addSite(Site site){
        sites.add(site);
    }
}

