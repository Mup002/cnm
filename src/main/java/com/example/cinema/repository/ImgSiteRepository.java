package com.example.cinema.repository;

import com.example.cinema.entity.ImgSite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImgSiteRepository extends JpaRepository<ImgSite, Long> {
}
