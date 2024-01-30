package com.example.cinema.repository;

import com.example.cinema.entity.Site;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SiteRepository extends JpaRepository<Site, Long> {
    Site findSiteById(Long id);
    List<Site> findSiteByAddress_Id(Long id);
    List<Site> findSiteByMovies_Id(Long id);
//    Site findSiteByNameAndCinema(String name, String cinema);
}
