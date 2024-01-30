package com.example.cinema.service;

import com.example.cinema.dto.request.TicketCustomeRequest;
import com.example.cinema.dto.request.TicketRequest;
import com.example.cinema.dto.response.TicketResponse;
import com.example.cinema.repository.TicketRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public interface TicketService {
    String createNewTicket(@RequestBody TicketRequest request);
    List<TicketResponse> getAllTicketBySiteAndShowtime(TicketCustomeRequest request);
    List<TicketResponse> getTicketsByUserId(Long userid);
}
