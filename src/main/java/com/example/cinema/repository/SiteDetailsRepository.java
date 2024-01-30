package com.example.cinema.repository;

import com.example.cinema.entity.SiteDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SiteDetailsRepository extends JpaRepository<SiteDetails,Long> {
    List<SiteDetails> findSiteDetailsBySiteId(Long idSite);

}
