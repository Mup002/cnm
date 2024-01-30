package com.example.cinema.service.impl;

import com.example.cinema.entity.*;
import com.example.cinema.repository.*;
import com.example.cinema.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final MovieRepository movieRepository;
    private final ShowtimeRepository showtimeRepository;
    private final SiteRepository siteRepository;
    private final ShowtimeSiteRepository showtimeSiteRepository;
    @Override
    public List<String> findByDayAndMovie(Long idMovie, String date) {
        List<String> result = new ArrayList<>();
        Movie movie = movieRepository.findMovieById(idMovie);
//        List<Showtime> showtimeList = showtimeRepository.
        List<Site> sites = siteRepository.findSiteByMovies_Id(idMovie);
        for(Site s: sites){
            List<ShowtimeSite> ss = showtimeSiteRepository.findShowtimeSiteBySiteId(s.getId());
            for(ShowtimeSite show : ss){
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                String formatted = format.format(show.getShowtime().getDay());
                if(date.equals(formatted)){
                    if(result.isEmpty() || !result.contains(s.getAddress().getName())) {
                        result.add(s.getAddress().getName());
                    }
                }
            }
        }
        return result;
    }
}
