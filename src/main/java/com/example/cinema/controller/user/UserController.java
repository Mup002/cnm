package com.example.cinema.controller.user;

import com.example.cinema.dto.response.TicketResponse;
import com.example.cinema.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
@CrossOrigin
public class UserController {
    private final TicketService ticketService;
    @GetMapping("/getAllTicketOfUser/{id}")
    public ResponseEntity<List<TicketResponse>> getAllTicketOfUser(@PathVariable("id") Long id){
        List<TicketResponse> responses = ticketService.getTicketsByUserId(id);
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }
}
