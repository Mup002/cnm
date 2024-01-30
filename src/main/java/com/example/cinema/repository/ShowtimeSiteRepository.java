package com.example.cinema.repository;

import com.example.cinema.entity.ShowtimeSite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ShowtimeSiteRepository extends JpaRepository<ShowtimeSite, Long> {
    ShowtimeSite findShowtimeSiteBySiteIdAndShowtimeId(Long idSite, Long idShowtimeId);
    List<ShowtimeSite> findShowtimeSiteBySiteId(Long id);
}
