package com.example.cinema.controller.user;

import com.example.cinema.dto.request.TicketCustomeRequest;
import com.example.cinema.dto.request.TicketRequest;
import com.example.cinema.dto.response.SiteDetailsResponse;
import com.example.cinema.dto.response.TicketResponse;
import com.example.cinema.service.BookingService;
import com.example.cinema.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/booking")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;
    private final TicketService ticketService;
    @GetMapping("/detailsSite/{id}")
    public ResponseEntity<List<SiteDetailsResponse>> getDetail(@PathVariable("id")Long id){
        List<SiteDetailsResponse> siteDetailsResponses = bookingService.getSiteDetails(id);
        return new ResponseEntity<>(siteDetailsResponses, HttpStatus.OK);
    }
    @PostMapping("/createdTicket")
    public String createdTicket(@RequestBody TicketRequest request){
        String status = ticketService.createNewTicket(request);
        return status;
    }

    @PostMapping("/getAllTicket")
    public ResponseEntity<?> getAllTicket(@RequestBody TicketCustomeRequest request){
        List<TicketResponse> responses = ticketService.getAllTicketBySiteAndShowtime(request);
        return new ResponseEntity<>(responses,HttpStatus.OK);
    }

}
