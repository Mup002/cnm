package com.example.cinema.repository;

import com.example.cinema.dto.response.TicketResponse;
import com.example.cinema.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByShowtimeSiteIdAndMovieId(Long showtimeSiteId, Long movieId);
    List<Ticket> findTicketByUserId(Long userid);
}
