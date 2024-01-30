package com.example.cinema.service.impl;

import com.example.cinema.dto.request.TicketCustomeRequest;
import com.example.cinema.dto.request.TicketRequest;
import com.example.cinema.dto.response.TicketResponse;
import com.example.cinema.entity.Ticket;
import com.example.cinema.repository.*;
import com.example.cinema.service.TicketService;
import com.example.cinema.utils.mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;
    private final ShowtimeRepository showtimeRepository;
    private final ShowtimeSiteRepository showtimeSiteRepository;
    @Override
    public String createNewTicket(TicketRequest request) {
        for(String s : request.getSeatNumbers()){
            Ticket ticket = new Ticket();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date formated = new Date();
            try{
                formated = format.parse(request.getTime());
            }catch (ParseException e){

            }
            ticket.setCreatedDate(formated);
            ticket.setTotalPrice(request.getTotalPrice());
            ticket.setSeatType(request.getSeatType());
            ticket.setUser(userRepository.findUserByUsername(request.getUserName()));
            ticket.setMovie(movieRepository.findMovieByName(request.getMovieName()));
            ticket.setSeatNumber(s);
            try{
                formated = format.parse(request.getDay());
            }catch (ParseException e){

            }
            Long idShowtime = showtimeRepository.findShowtimeByTimeAndDay(request.getTime(),formated).getId();
            ticket.setShowtimeSite(showtimeSiteRepository.findShowtimeSiteBySiteIdAndShowtimeId(request.getIdSite(), idShowtime));
            ticketRepository.save(ticket);
        }

        return "done";
    }

    @Override
    public List<TicketResponse> getAllTicketBySiteAndShowtime(TicketCustomeRequest request) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date formated = new Date();
        try{
            formated = format.parse(request.getDay());
        }catch (ParseException e){

        }
        Long idShowtime = showtimeRepository.findShowtimeByTimeAndDay(request.getTime(),formated).getId();
        Long idShowtimeSite = showtimeSiteRepository.findShowtimeSiteBySiteIdAndShowtimeId(request.getIdSite(), idShowtime).getId();

        return mapper.mapperToLstTicketResponse(ticketRepository.findByShowtimeSiteIdAndMovieId(idShowtimeSite, request.getIdMovie()).stream().collect(Collectors.toList()));
    }

       @Override
    public List<TicketResponse> getTicketsByUserId(Long userid) {
        List<Ticket> tickets = ticketRepository.findTicketByUserId(userid);
        return mapper.mapperToLstTicketResponse(tickets).stream().collect(Collectors.toList());
    }
}
