package com.example.cinema.service.impl;

import com.example.cinema.dto.request.ShowtimeCustomRequest;
import com.example.cinema.dto.response.*;
import com.example.cinema.entity.*;
import com.example.cinema.repository.*;
import com.example.cinema.service.SiteService;
import com.example.cinema.utils.mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SiteServiceImpl implements SiteService {
    private final SiteRepository siteRepository;
    private final MovieRepository movieRepository;
    private final ShowtimeSiteRepository showtimeSiteRepository;
    private final ShowtimeRepository showtimeRepository;
    private final AddressRepository addressRepository;

    @Override
    public List<SiteCustomResponse> getAllSiteByMovie(ShowtimeCustomRequest request) {
        Movie movie = movieRepository.findMovieById(request.getIdMovie());
        Address address =  addressRepository.findAddressByName(request.getAddress());
        List<Site> sites = siteRepository.findSiteByMovies_Id(request.getIdMovie());
        Map<String , List<Object>> map = new HashMap<>();
        List<SiteCustomResponse> responseList = new ArrayList<>();

        for(Site site : sites){
            if(site.getAddress().getName() == address.getName()){
                List<Object> cinemaLst = new ArrayList<>();
                if(map.isEmpty() || !map.containsKey(site.getName())){
//                    List<ShowtimeSite> showtimeSites = showtimeSiteRepository.findShowtimeSiteBySiteId(site.getId());
//                    Map<String, List<String>> mapCnm = new HashMap<>();
//                    for(ShowtimeSite ss : showtimeSites){
//                        if(mapCnm.isEmpty() || !mapCnm.containsKey(ss.getShowtime().getType_cinema())){
//                            List<String> showtimes = new ArrayList<>();
//                            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//                            String formatted = format.format(ss.getShowtime().getDay());
//                            if(formatted.equals(request.getDay())){
//                                showtimes.add(ss.getShowtime().getTime());
//                            }
//                            mapCnm.put(ss.getShowtime().getType_cinema(),showtimes);
//                        }else if(mapCnm.containsKey(ss.getShowtime().getType_cinema())){
//                            List<String> showtimes = mapCnm.get(ss.getShowtime().getType_cinema());
//                            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//                            String formatted = format.format(ss.getShowtime().getDay());
//                            if(formatted.equals(request.getDay())){
//                                showtimes.add(ss.getShowtime().getTime());
//                            }
//                            mapCnm.put(ss.getShowtime().getType_cinema(),showtimes);
//                        }
//                    }
//                    for(Map.Entry<String, List<String>> entry : mapCnm.entrySet()){
//                        CinemaCustomResponse cinemaCustomResponse = new CinemaCustomResponse();
//                        cinemaCustomResponse.setIdSite(site.getId());
//                        cinemaCustomResponse.setCinemaType(entry.getKey());
//                        cinemaCustomResponse.setShowtimes(entry.getValue());
//                        cinemaLst.add(cinemaCustomResponse);
//                    }
//                    map.put(site.getName(),cinemaLst);
                }else if(map.containsKey(site.getName())){
                    cinemaLst = map.get(site.getName());
                }
                List<ShowtimeSite> showtimeSites = showtimeSiteRepository.findShowtimeSiteBySiteId(site.getId());
                Map<String, List<String>> mapCnm = new HashMap<>();
                for(ShowtimeSite ss : showtimeSites){
                    List<String> showtimes = new ArrayList<>();
                    if(mapCnm.isEmpty() || !mapCnm.containsKey(ss.getShowtime().getType_cinema())){

//                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//                        String formatted = format.format(ss.getShowtime().getDay());
//                        if(formatted.equals(request.getDay())){
//                            showtimes.add(ss.getShowtime().getTime());
//                        }
//                        mapCnm.put(ss.getShowtime().getType_cinema(),showtimes);
                    }else if(mapCnm.containsKey(ss.getShowtime().getType_cinema())){
                        showtimes = mapCnm.get(ss.getShowtime().getType_cinema());
                    }
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    String formatted = format.format(ss.getShowtime().getDay());
                    if(formatted.equals(request.getDay())){
                        showtimes.add(ss.getShowtime().getTime());
                    }
                    mapCnm.put(ss.getShowtime().getType_cinema(),showtimes);
                }
                for(Map.Entry<String, List<String>> entry : mapCnm.entrySet()){
                    CinemaCustomResponse cinemaCustomResponse = new CinemaCustomResponse();
                    cinemaCustomResponse.setIdSite(site.getId());
                    cinemaCustomResponse.setCinemaType(entry.getKey());
                    cinemaCustomResponse.setShowtimes(entry.getValue());
                    cinemaLst.add(cinemaCustomResponse);
                }
                map.put(site.getName(),cinemaLst);
            }
        }
        for(Map.Entry<String, List<Object>> entry : map.entrySet()){
            SiteCustomResponse customResponse = new SiteCustomResponse();
            String key = entry.getKey();
            customResponse.setAddress(address.getName());
            customResponse.setName(key);

            List<Object> objects = entry.getValue();
            if(!objects.isEmpty()){
                customResponse.setList(objects);
                responseList.add(customResponse);
            }
        }

        return responseList;
    }

    @Override
    public List<AddressResponse> getAllAddressSite() {
        List<AddressResponse> responses = new ArrayList<>();
        addressRepository.findAll().forEach(a -> responses.add(new AddressResponse(a.getId(),a.getName())));
        return responses.stream().collect(Collectors.toList());
    }

    @Override
    public List<SiteNameResponse> getAllSiteByAddress(Long addressId) {
        List<Site> sites = siteRepository.findSiteByAddress_Id(addressId);
        List<SiteNameResponse> responses = new ArrayList<>();
        Set<String> nameLst = new HashSet<>();
        for(Site s : sites){
            if(nameLst.isEmpty() || !nameLst.contains(s.getName())){
                nameLst.add(s.getName());
                responses.add(new SiteNameResponse(s.getName()));
            }
        }
        return responses;
    }


}
