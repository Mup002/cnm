package com.example.cinema.utils;

import com.example.cinema.dto.response.MovieResponse;
import com.example.cinema.dto.response.SeatResponse;
import com.example.cinema.dto.response.SiteDetailsResponse;
import com.example.cinema.dto.response.TicketResponse;
import com.example.cinema.entity.*;
import org.springframework.util.ObjectUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class mapper {
    public static MovieResponse mapperToMovieResponse(Movie movie){
        MovieResponse response = new MovieResponse();
        response.setId(movie.getId());
        response.setName(movie.getName());
        response.setImg(movie.getImg());
        response.setActor(movie.getActor());
        response.setDirector(movie.getDirector());
        response.setLanguage(movie.getLanguage());
        response.setDescription(movie.getDescription());
        if(movie.getRating() != null){
            response.setRating(movie.getRating());
        }else{
            response.setRating("Chưa có đánh giá");
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        response.setStartTime(String.valueOf(format.format(movie.getStartTime())));
        response.setTiming(movie.getTiming());
        List<String> categoriesName = new ArrayList<>();
        if(!ObjectUtils.isEmpty(movie.getCategories())){
            List<Category> categories = movie.getCategories();
            for(Category c : categories){
                categoriesName.add(c.getName());
            }
        }
        response.setCategoriesName(categoriesName);
        return response;
    }

    public static List<MovieResponse> mapperToLstMovieResponse(List<Movie> movielist){
        List<MovieResponse> movieResponseList = new ArrayList<>();
        for(Movie m : movielist){
            movieResponseList.add(mapperToMovieResponse(m));
        }
        return movieResponseList;
    }

    public static SeatResponse mapperToSeatResponse(Seat seat){
        SeatResponse response = new SeatResponse();
        response.setId_seat(seat.getId());
        response.setSeat_name(seat.getSeat_name());
        return response;
    }

    public static List<SeatResponse> mapperToLstSeatResponse(List<Seat> seats){
        List<SeatResponse> seatResponseList = new ArrayList<>();
        for(Seat seat: seats){
            seatResponseList.add(mapperToSeatResponse(seat));
        }
        return seatResponseList;
    }

    public static SiteDetailsResponse mapperToSiteDetailsResponse(SiteDetails siteDetails){
        SiteDetailsResponse siteDetailsResponse = new SiteDetailsResponse();
        siteDetailsResponse.setId_details(siteDetails.getId());
        siteDetailsResponse.setPrice_seat(siteDetails.getPrice_seat());
        siteDetailsResponse.setQuantity_seats(siteDetails.getQuantity_seats());
        siteDetailsResponse.setSeat_type(siteDetails.getSeat_type());
        siteDetailsResponse.setId_site(siteDetails.getSite().getId());
        return siteDetailsResponse;
    }
    public static List<SiteDetailsResponse> mapperToLstSiteDetailsResponse(List<SiteDetails> siteDetailsLst){
        List<SiteDetailsResponse> siteDetailsResponses = new ArrayList<>();
        for(SiteDetails s : siteDetailsLst){
            siteDetailsResponses.add(mapperToSiteDetailsResponse(s));
        }
        return siteDetailsResponses;
    }

    public static TicketResponse mapperToTicketResponse(Ticket ticket){
        TicketResponse ticketResponse = new TicketResponse();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        ticketResponse.setSeatNumber(ticket.getSeatNumber());
        ticketResponse.setSeatType(ticket.getSeatType());
        ticketResponse.setTotalPrice(ticket.getTotalPrice());
        ticketResponse.setUserName(ticket.getUser().getUsername());
        ticketResponse.setMovieName(ticket.getMovie().getName());
        ticketResponse.setSiteName(ticket.getShowtimeSite().getSite().getName());
//        ticketResponse.setCinema(ticket.getShowtimeSite().getSite().getCinema());
        ticketResponse.setCreated(format.format(ticket.getCreatedDate()));
        ticketResponse.setTime(ticket.getShowtimeSite().getShowtime().getTime());
        ticketResponse.setDay(format.format(ticket.getShowtimeSite().getShowtime().getDay()));
        return ticketResponse;
    }

    public static List<TicketResponse> mapperToLstTicketResponse(List<Ticket> ticketList){
        List<TicketResponse> ticketResponseList = new ArrayList<>();
        for(Ticket t : ticketList){
            ticketResponseList.add(mapperToTicketResponse(t));
        }

        return ticketResponseList;
    }
}
