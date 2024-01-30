package com.example.cinema.service;

import com.example.cinema.dto.request.ShowtimeCustomRequest;
import com.example.cinema.dto.response.AddressResponse;
import com.example.cinema.dto.response.SiteCustomResponse;
import com.example.cinema.dto.response.SiteDetailsResponse;
import com.example.cinema.dto.response.SiteNameResponse;
import com.example.cinema.entity.Address;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SiteService {
    List<SiteCustomResponse> getAllSiteByMovie(ShowtimeCustomRequest request);
    List<AddressResponse> getAllAddressSite();
    List<SiteNameResponse> getAllSiteByAddress(Long addressId);

}
